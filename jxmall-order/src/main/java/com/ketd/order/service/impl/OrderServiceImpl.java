package com.ketd.order.service.impl;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.ketd.common.api.cart.CartOpenFeignApi;
import com.ketd.common.api.member.MemberReceiveAddressOpenFeignApi;
import com.ketd.common.api.product.SkuInfoOpenFeignApi;
import com.ketd.common.api.product.SpuInfoOpenFeignApi;
import com.ketd.common.api.ware.WareSkuOpenFeignApi;
import com.ketd.common.domain.member.MemberReceiveAddressTO;
import com.ketd.common.domain.member.MemberTO;
import com.ketd.common.domain.order.LockStickResult;
import com.ketd.common.domain.product.SkuInfoTO;
import com.ketd.common.domain.product.SpuInfoTO;
import com.ketd.common.result.Result;
import com.ketd.order.Interceptors.LoginProtectedInterceptor;
import com.ketd.order.domain.OrderItem;
import com.ketd.order.enume.OrderStatusEnum;
import com.ketd.order.mapper.OrderItemMapper;
import com.ketd.order.service.IOrderItemService;
import com.ketd.order.util.RedisUtil;
import com.ketd.order.vo.*;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.servlet.http.HttpServletResponse;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.order.mapper.OrderMapper;
import com.ketd.order.domain.Order;
import com.ketd.order.service.IOrderService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 订单Service业务层处理
 *
 * @author ketd
 * @date 2024-05-27
 */
@Service
@Primary
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper  orderItemMapper;

    @Autowired
    private IOrderItemService  orderItemService;

    @Autowired
    private MemberReceiveAddressOpenFeignApi memberReceiveAddressOpenFeignApi;

    @Autowired
    private SkuInfoOpenFeignApi skuInfoOpenFeignApi;

    @Autowired
    private SpuInfoOpenFeignApi spuInfoOpenFeignApi;

    @Autowired
    private WareSkuOpenFeignApi wareSkuOpenFeignApi;

    @Autowired
    private CartOpenFeignApi cartOpenFeignApi;

    @Autowired
    private ThreadPoolExecutor executor;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedissonClient redisson;

    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public Order selectOrderById(Long id)
    {
        return orderMapper.selectById(id);
    }



    /**
     * 查询订单列表
     *
     * @param order 订单
     * @return 订单
     */
    @Override
    public List<Order> selectOrderList(Order order)
    {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>(order);
        return orderMapper.selectList(queryWrapper);
    }

    /**
     * 新增订单
     *
     * @param order 订单
     * @return 结果
     */

    @Override
    public int insertOrder(Order order) {
        return orderMapper.insert(order);
    }





    /**
     * 修改订单
     *
     * @param order 订单
     * @return 结果
     */

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateById(order);
    }

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的订单主键集合
     * @return 结果
     */
    @Override
    public int deleteOrderByIds(Long[] ids) {
        return orderMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteOrderById(Long id) {
        return orderMapper.deleteById(id);
    }


    /**
     * 导出订单列表
     */
    @Override
    public void export(List<Order> list, HttpServletResponse response) {

        extracted(list, response,Order.class);

    }

    @Override
    public Result<?> toTrade(List<SkuCountVo> skuCountVos) {
        try {
            OrderConfirmVo orderConfirmVo = new OrderConfirmVo();

            // 获取当前用户信息
            MemberTO currentMember = getCurrentMember();
            Long memberId = currentMember.getId();
            Long memberIntegration = currentMember.getIntegration();

            // 启动异步任务来获取地址列表
            CompletableFuture<Void> addressFuture = CompletableFuture.runAsync(() -> {
                List<MemberReceiveAddressTO> memberAddress = getMemberAddress(memberId);

                List<MemberAddressVo> memberAddressVos = Optional.ofNullable(memberAddress)
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .map(addressTO -> {
                            MemberAddressVo addressVo = new MemberAddressVo();
                            BeanUtils.copyProperties(addressTO, addressVo);
                            return addressVo;
                        })
                        .collect(Collectors.toList());

                orderConfirmVo.setAddresses(memberAddressVos);
            }, executor);

            // 设置当前用户的积分
            orderConfirmVo.setIntegration(memberIntegration);

            // 启动异步任务来处理订单项
            CompletableFuture<Void> orderItemVosFuture = CompletableFuture.supplyAsync(() -> {
                List<OrderItemVo> orderItemVos = new ArrayList<>();
                BigDecimal totalPayAmount = BigDecimal.ZERO;

                for (SkuCountVo orderContVo : skuCountVos) {
                    OrderItemVo orderItemVo = new OrderItemVo();
                    SkuInfoTO skuInfoTO = skuInfoOpenFeignApi.getInfo(orderContVo.getSkuId()).getData();

                    orderItemVo.setSkuId(skuInfoTO.getSkuId());
                    orderItemVo.setTitle(skuInfoTO.getSkuTitle());
                    orderItemVo.setImage(skuInfoTO.getSkuDefaultImg());
                    orderItemVo.setSkuAttrValues(skuInfoTO.getSkuAttrValues());
                    orderItemVo.setPrice(skuInfoTO.getPrice());
                    orderItemVo.setCount(orderContVo.getSkuCont());

                    orderItemVos.add(orderItemVo);
                    BigDecimal itemTotalPrice = skuInfoTO.getPrice().multiply(new BigDecimal(orderContVo.getSkuCont()));
                    totalPayAmount = totalPayAmount.add(itemTotalPrice);
                }

                orderConfirmVo.setItems(orderItemVos);
                orderConfirmVo.setTotalPayAmount(totalPayAmount);
                orderConfirmVo.setPayPrice(totalPayAmount);

                return orderItemVos;
            }, executor).thenAcceptAsync(orderItemVos -> {
                for (OrderItemVo orderItemVo : orderItemVos) {
                    Long skuId = orderItemVo.getSkuId();
                    Integer count = orderItemVo.getCount();
                    Boolean hasStock = wareSkuOpenFeignApi.hasStockByCount(skuId, count).getData();
                    orderItemVo.setHasStock(Objects.requireNonNullElse(hasStock, false));
                }
            }, executor);

            // 生成并设置订单令牌
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            redisUtil.set("order-token:" + memberId, token, 300);
            orderConfirmVo.setOrderToken(token);

            // 等待所有异步任务完成
            CompletableFuture.allOf(addressFuture, orderItemVosFuture).get();
            return Result.ok(orderConfirmVo);
        } catch (BeansException | InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    @GlobalTransactional
    public Result<?> submitOrder(SubmitOrderVo submitOrderVo) {
        OrderCreateTo orderCreateTo = new OrderCreateTo();
        MemberTO currentMember = getCurrentMember();
        String orderToken = submitOrderVo.getOrderToken();

        // 验证令牌是否合法【令牌的对比和删除必须保证原子性】
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        try {
            Long result = redisUtil.execute(script, "order-token:" + currentMember.getId(), orderToken);
            if (result == 0L) {
                // 令牌不匹配或未找到
                return Result.error("订单已过期");
            }

            // 令牌匹配并已删除
            Order order = createOrder(submitOrderVo);
            List<OrderItem> orderItemList = createOrderItems(submitOrderVo.getSkuCountVoList(), order);


            com.ketd.common.domain.order.WareSkuLockTo wareSkuLockTo = getWareSkuLockTo(submitOrderVo, order);
            List<LockStickResult> lockStickResultList=  wareSkuOpenFeignApi.orderLockStock(wareSkuLockTo).getData();


            // 验证价格
            BigDecimal totalAmount = computeTotalAmount(orderItemList);
            BigDecimal payAmount = computePayAmount(orderItemList);
            order.setTotalAmount(totalAmount);
            order.setPayAmount(payAmount.add(order.getFreightAmount()));

            BigDecimal weight = orderItemList.stream().map(OrderItem::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
            order.setWeight(weight);

            Long integration = orderItemList.stream().mapToLong(OrderItem::getGiftIntegration).sum();
            Long growth = orderItemList.stream().mapToLong(OrderItem::getGiftGrowth).sum();

            // 积分&成长值
            order.setIntegration(integration);
            order.setGrowth(growth);

            // 保存订单
            orderCreateTo.setOrder(order);
            orderCreateTo.setOrderItemList(orderItemList);
            orderCreateTo.setPayAmount(payAmount);
            orderCreateTo.setFare(order.getFreightAmount());



            boolean lockStock=true;
            for (LockStickResult lockStickResult : lockStickResultList) {
                if (!lockStickResult.getLockStock()) {
                    lockStock=false;
                    break;
                }
            }
            if (lockStock) {
                saveOrder(orderCreateTo);
                List<SkuCountVo> skuCountVoList = submitOrderVo.getSkuCountVoList();
                Long[] skuIds = new Long[skuCountVoList.size()];

                for (int i = 0; i < skuCountVoList.size(); i++) {
                    skuIds[i] = skuCountVoList.get(i).getSkuId();
                }


                cartOpenFeignApi.delete(skuIds);
                /*return   Result.ok(orderCreateTo);*/
                return   Result.ok(order.getId());
            }else {
                return Result.error("库存不足");
            }

        } catch (Exception e) {
            System.err.println(e);
            return Result.error("系统异常");
        }
    }



    private static com.ketd.common.domain.order.WareSkuLockTo getWareSkuLockTo(SubmitOrderVo submitOrderVo, Order order) {
        com.ketd.common.domain.order.WareSkuLockTo  wareSkuLockTo = new com.ketd.common.domain.order.WareSkuLockTo();
        wareSkuLockTo.setOrderSn(order.getOrderSn());
        List<com.ketd.common.domain.order.SkuCountVo>  skuCountVoList = new ArrayList<>();
        for(SkuCountVo skuCountVo: submitOrderVo.getSkuCountVoList()){
            com.ketd.common.domain.order.SkuCountVo  skuCountVo1 = new com.ketd.common.domain.order.SkuCountVo();
            skuCountVo1.setSkuId(skuCountVo.getSkuId());
            skuCountVo1.setSkuCont(skuCountVo.getSkuCont());
            skuCountVoList.add(skuCountVo1);
        }
        wareSkuLockTo.setLocks(skuCountVoList);
        return wareSkuLockTo;
    }

    private void saveOrder(OrderCreateTo orderCreateTo) {
        Order order = orderCreateTo.getOrder();
        order.setCreateTime(new Date());
        orderMapper.insert(order);

        orderCreateTo.getOrderItemList().forEach(orderItem -> {
            orderItem.setOrderId(order.getId());
        });
        orderItemService.saveBatch(orderCreateTo.getOrderItemList());
    }

    private BigDecimal computePayAmount(List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(OrderItem::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal computeTotalAmount(List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(OrderItem::getRealAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    private List<OrderItem> createOrderItems(List<SkuCountVo> skuCountVoList, Order order) {
        return skuCountVoList.stream()
                .map(skuCountVo -> createOrderItem(skuCountVo, order))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private OrderItem createOrderItem(SkuCountVo skuCountVo, Order order) {
        SkuInfoTO skuInfoTO = skuInfoOpenFeignApi.getInfo(skuCountVo.getSkuId()).getData();
        if (skuInfoTO == null) {
            return null;
        }
        SpuInfoTO  spuInfoTO = spuInfoOpenFeignApi.getInfo(skuInfoTO.getSpuId()).getData();

        if (spuInfoTO == null) {
            return null;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderSn(order.getOrderSn());

        // 将 SkuInfoTO 映射到 OrderItem
        orderItem.setSkuId(skuInfoTO.getSkuId());

        orderItem.setSkuName(skuInfoTO.getSkuName());
        orderItem.setSkuPic(skuInfoTO.getSkuDefaultImg());
        orderItem.setSkuPrice(skuInfoTO.getPrice());
        orderItem.setCategoryId(skuInfoTO.getCatalogId());

        orderItem.setSkuAttrsVals(skuInfoTO.getSkuAttrValues());

        orderItem.setSpuId(skuInfoTO.getSpuId());
        orderItem.setSpuName(spuInfoTO.getSpuName());
        orderItem.setSpuBrand(spuInfoTO.getBrandName());
        // SkuCountVo 中的其他字段
        orderItem.setSkuQuantity((long) skuCountVo.getSkuCont());

        // 设置折扣金额（这些值可以是常量或计算值）
        BigDecimal promotionAmount = BigDecimal.ONE;
        BigDecimal couponAmount = new BigDecimal("2.00");
        BigDecimal integrationAmount = new BigDecimal("3.00");
        orderItem.setPromotionAmount(promotionAmount);
        orderItem.setCouponAmount(couponAmount);
        orderItem.setIntegrationAmount(integrationAmount);

        BigDecimal weight = spuInfoTO.getWeight();
        orderItem.setWeight(weight.multiply(BigDecimal.valueOf(skuCountVo.getSkuCont())));

        // 计算 totalAmount 和 realAmount
        BigDecimal totalAmount = skuInfoTO.getPrice().multiply(BigDecimal.valueOf(skuCountVo.getSkuCont()));
        orderItem.setTotalAmount(totalAmount);

        BigDecimal realAmount = totalAmount
                .subtract(promotionAmount)
                .subtract(couponAmount)
                .subtract(integrationAmount);
        orderItem.setRealAmount(realAmount);

        // 计算礼物整合和增长
        orderItem.setGiftIntegration(realAmount.divide(BigDecimal.valueOf(100), RoundingMode.DOWN).longValue());
        orderItem.setGiftGrowth(realAmount.divide(BigDecimal.valueOf(10), RoundingMode.DOWN).longValue());

        return orderItem;
    }




    private Order createOrder(SubmitOrderVo submitOrderVo) {
        // 获取当前用户信息和地址列表
        MemberTO currentMember = getCurrentMember();
        List<MemberReceiveAddressTO> addressList = getMemberAddress(currentMember.getId());

        // 查找提交订单中的地址
        MemberReceiveAddressTO address = addressList.stream()
                .filter(addr -> addr.getId().equals(submitOrderVo.getAddrId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Address not found for id: " + submitOrderVo.getAddrId()));

        // 创建订单
        Order order = new Order();
        String orderSn = IdWorker.getTimeId(); // 雪花算法生成订单号
        order.setOrderSn(orderSn);
        order.setMemberId(currentMember.getId());
        order.setCouponId(14L); // 示例固定值，可根据实际情况调整
        order.setCreateTime(new Date());
        order.setMemberUsername(currentMember.getUsername());

        // 设置收货地址信息
        order.setReceiverName(address.getName());
        order.setReceiverPhone(address.getPhone());
        order.setReceiverPostCode(address.getPostCode());
        order.setReceiverProvince(address.getProvince());
        order.setReceiverCity(address.getCity());
        order.setReceiverRegion(address.getRegion());
        order.setReceiverDetailAddress(address.getDetailAddress());

        // 设置订单备注和运费
        order.setNote(submitOrderVo.getRemark());
        order.setFreightAmount(BigDecimal.TEN); // 示例运费固定值10，可根据实际情况调整

        // 设置订单状态和自动确认天数
        order.setStatus(OrderStatusEnum.CREATE_NEW.getCode());
        order.setAutoConfirmDay(7); // 示例自动确认天数

        return order;
    }


    // 修改 getMemberAddress 方法，传递当前用户信息
    public List<MemberReceiveAddressTO> getMemberAddress(Long memberId) {
        try {
            return memberReceiveAddressOpenFeignApi.getMemberAddressById(memberId).getData();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Result<?> getMemberOrders(Integer status) {
        if (status == null) {
            Long memberId = getCurrentMember().getId();
            List<OrdersVo> ordersVoList = new ArrayList<>();
            List<Order> orders = orderMapper.selectAllByMemberId(memberId);
            getOrderItems(ordersVoList, orders);
            return Result.ok(ordersVoList);
        } else {
            return   Result.ok(getOrdersVoList(getCurrentMember().getId(), status));
        }
    }
    private List<OrdersVo>  getOrdersVoList(Long memberId,Integer status){
        List<OrdersVo> ordersVoList  = new ArrayList<>();
        List<Order> orders = orderMapper.selectAllByMemberIdAndStatus(memberId,status);
        getOrderItems(ordersVoList, orders);
        return ordersVoList;
    }

    private void getOrderItems(List<OrdersVo> ordersVoList, List<Order> orders) {
        for(Order order:orders){
            OrdersVo ordersVo=new OrdersVo();
            List<OrderItem> orderItemList = orderItemMapper.selectAllByOrderId(order.getId());
            ordersVo.setOrder(order);
            ordersVo.setOrderItem(orderItemList);
            ordersVoList.add(ordersVo);
        }
    }

    @Override
    public Object getMemberOrderInfo(Long id) {
        return orderMapper.selectOneByIdAndMemberId(id,getCurrentMember().getId());
    }

    private MemberTO getCurrentMember() {
        return LoginProtectedInterceptor.threadLocal.get();
    }

}