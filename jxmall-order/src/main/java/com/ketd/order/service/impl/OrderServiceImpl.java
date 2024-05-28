package com.ketd.order.service.impl;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import cn.hutool.core.lang.UUID;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.ketd.common.api.member.MemberReceiveAddressOpenFeignApi;
import com.ketd.common.api.product.SkuInfoOpenFeignApi;
import com.ketd.common.api.ware.WareSkuOpenFeignApi;
import com.ketd.common.domain.member.MemberReceiveAddressTO;
import com.ketd.common.domain.member.MemberTO;
import com.ketd.common.domain.product.SkuInfoTO;
import com.ketd.common.result.Result;
import com.ketd.order.Interceptors.LoginProtectedInterceptor;
import com.ketd.order.domain.OrderItem;
import com.ketd.order.enume.OrderStatusEnum;
import com.ketd.order.mapper.OrderItemMapper;
import com.ketd.order.service.IOrderItemService;
import com.ketd.order.util.RedisUtil;
import com.ketd.order.vo.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletResponse;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
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
    private WareSkuOpenFeignApi wareskuOpenFeignApi;

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

            // 在主线程中获取当前用户信息
            MemberTO currentMember = getCurrentMember();

            // 启动异步任务来获取地址列表
            CompletableFuture<Void> addressFuture = CompletableFuture.runAsync(() -> {
                // 获取成员地址列表
                List<MemberReceiveAddressTO> memberAddress = getMemberAddress(currentMember.getId());

                // 创建目标列表
                List<MemberAddressVo> memberAddressVos = new ArrayList<>();

                if (memberAddress != null) {
                    // 遍历源列表，并复制属性到目标列表中的每个对象
                    for (MemberReceiveAddressTO addressTO : memberAddress) {
                        MemberAddressVo addressVo = new MemberAddressVo();
                        BeanUtils.copyProperties(addressTO, addressVo);
                        memberAddressVos.add(addressVo);
                    }
                } else {
                    System.err.println("memberAddress is null");
                }

                orderConfirmVo.setAddresses(memberAddressVos);
            }, executor);

            // 设置当前用户的积分
            orderConfirmVo.setIntegration(currentMember.getIntegration());

            // 启动异步任务来处理订单项
            CompletableFuture<Void> orderItemVosFuture = CompletableFuture.runAsync(() -> {
                List<OrderItemVo> orderItemVos = new ArrayList<>();
                BigDecimal totalPayAmount = BigDecimal.ZERO;

                for (SkuCountVo orderContVo : skuCountVos) {
                    // 创建订单项
                    OrderItemVo orderItemVo = new OrderItemVo();
                    SkuInfoTO skuInfoTO = skuInfoOpenFeignApi.getInfo(orderContVo.getSkuId()).getData();
                    orderItemVo.setSkuId(skuInfoTO.getSkuId());
                    orderItemVo.setTitle(skuInfoTO.getSkuTitle());
                    orderItemVo.setImage(skuInfoTO.getSkuDefaultImg());
                    orderItemVo.setSkuAttrValues(skuInfoTO.getSkuAttrValues());
                    orderItemVo.setPrice(skuInfoTO.getPrice());
                    orderItemVo.setCount(orderContVo.getSkuCont());

                    orderItemVos.add(orderItemVo);
                    // 计算此商品的总价
                    BigDecimal itemTotalPrice = skuInfoTO.getPrice().multiply(new BigDecimal(orderContVo.getSkuCont()));
                    // 添加到总付款金额
                    totalPayAmount = totalPayAmount.add(itemTotalPrice);
                }

                orderConfirmVo.setItems(orderItemVos);
                orderConfirmVo.setTotalPayAmount(totalPayAmount);
                orderConfirmVo.setPayPrice(totalPayAmount);
            }, executor).thenRunAsync(() -> {
                List<OrderItemVo> orderItemVos = orderConfirmVo.getItems();
                for (OrderItemVo orderItemVo : orderItemVos) {
                    Long skuId = orderItemVo.getSkuId();
                    Integer count = orderItemVo.getCount();
                    Boolean hasStock = wareskuOpenFeignApi.hasStockByCount(skuId, count).getData();
                    orderItemVo.setHasStock(Objects.requireNonNullElse(hasStock, false));
                }
            }, executor);

            String token = UUID.randomUUID().toString().replaceAll("-", "");
            redisUtil.set("order-token:"+ getCurrentMember().getId(), token,300);
            orderConfirmVo.setOrderToken(token);
            // 等待所有异步任务完成
            CompletableFuture.allOf(addressFuture, orderItemVosFuture).get();
            return Result.ok(orderConfirmVo);
        } catch (BeansException | InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Result<?> submitOrder(SubmitOrderVo submitOrderVo) {

        OrderCreateTo  orderCreateTo =new OrderCreateTo();
        MemberTO currentMember = getCurrentMember();
        String orderToken = submitOrderVo.getOrderToken();

       /* String script = "local token = redis.call('get', KEYS[1]);" +
                "if token == ARGV[1] then " +
                "    redis.call('del', KEYS[1]);" +
                "    return 1;" +
                "else " +
                "    return 0;" +
                "end";*/

        //1、验证令牌是否合法【令牌的对比和删除必须保证原子性】
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";


        try {
            Long result = redisUtil.execute(script, "order-token:" + currentMember.getId(), orderToken);
            if (result==0L){
                // Token did not match or was not found
                return Result.error("订单已过期");
            } else {
                // Token matched and was deleted
                Order order = creteOrder(submitOrderVo);

                //获取订单项
                List<OrderItem> orderItemList=  createOrderItems(submitOrderVo.getSkuCountVoList(),order);

                //验证价格
                BigDecimal totalAmount= computeTotalAmount(order,orderItemList);
                BigDecimal payAmount= computePayAmount(order,orderItemList);
                order.setTotalAmount(totalAmount);
                order.setPayAmount(payAmount.add(order.getFreightAmount()));

                Long integration = 0L;
                Long growth = 0L;
                for (OrderItem orderItem : orderItemList) {
                    integration += (orderItem.getGiftIntegration());
                    growth += (orderItem.getGiftGrowth());
                }
                //积分&成长值
                order.setIntegration(integration);
                order.setGrowth(growth);



                //保存订单
                orderCreateTo.setOrder(order);
                orderCreateTo.setOrderItemList(orderItemList);
                orderCreateTo.setPayAmount(payAmount);
                orderCreateTo.setFare(order.getFreightAmount());

                SaveOrder(orderCreateTo);


                return Result.ok(orderCreateTo);

            }
        } catch (Exception e) {
            System.out.println(e);
            return Result.error("系统异常");
        }
    }

    private void SaveOrder(OrderCreateTo  orderCreateTo) {
        Order order = orderCreateTo.getOrder();
        order.setCreateTime(new Date());
        orderMapper.insert(order);

        orderCreateTo.getOrderItemList().forEach(orderItem -> {
            orderItem.setOrderId(order.getId());
        });
        orderItemService.saveBatch(orderCreateTo.getOrderItemList());
    }



    private BigDecimal computePayAmount(Order order, List<OrderItem> orderItemList) {
        return orderItemList.stream().map(OrderItem::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal computeTotalAmount(Order order, List<OrderItem> orderItemList) {
        return orderItemList.stream().map(OrderItem::getRealAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<OrderItem> createOrderItems(List<SkuCountVo> skuCountVoList,  Order order) {
        return skuCountVoList.stream()
                .map(skuCountVo -> {
                    SkuInfoTO skuInfoTO = skuInfoOpenFeignApi.getInfo(skuCountVo.getSkuId()).getData();
                    if (skuInfoTO != null) {
                        OrderItem orderItem = new OrderItem();

                        orderItem.setOrderSn(order.getOrderSn());
                        // Mapping SkuInfoTO to OrderItem
                        orderItem.setSkuId(skuInfoTO.getSkuId());
                        orderItem.setSpuId(skuInfoTO.getSpuId());
                        orderItem.setSkuName(skuInfoTO.getSkuName());
                        orderItem.setSkuPic(skuInfoTO.getSkuDefaultImg());
                        orderItem.setSkuPrice(skuInfoTO.getPrice());
                        orderItem.setCategoryId(skuInfoTO.getCatalogId());
                        orderItem.setSpuBrand(skuInfoTO.getBrandName());
                        orderItem.setSkuAttrsVals(skuInfoTO.getSkuAttrValues());

                        // SkuCountVo 中的其他字段
                        orderItem.setSkuQuantity(Long.valueOf(skuCountVo.getSkuCont()));
                        //优惠详细
                        BigDecimal promotionAmount=new BigDecimal("1.00");
                        orderItem.setPromotionAmount(promotionAmount);
                        BigDecimal couponAmount= new BigDecimal("2.00");
                        orderItem.setCouponAmount(couponAmount);
                        BigDecimal integrationAmount= new BigDecimal("3.00");
                        orderItem.setIntegrationAmount(integrationAmount);
                        // Calculate realAmount as skuPrice * skuQuantity

                        BigDecimal totalAmount = skuInfoTO.getPrice()
                                .multiply(new BigDecimal(skuCountVo.getSkuCont()));


                        orderItem.setTotalAmount(totalAmount);

                        BigDecimal realAmount = totalAmount
                                .subtract(promotionAmount)
                                .subtract(couponAmount)
                                .subtract(integrationAmount);
                        orderItem.setRealAmount(realAmount);


                        orderItem.setGiftIntegration(realAmount.divide(new BigDecimal("100"), 0, RoundingMode.DOWN).longValue());
                        orderItem.setGiftGrowth(realAmount.divide(new BigDecimal("10"), 0, RoundingMode.DOWN).longValue());

                        return orderItem;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }



    private Order creteOrder(SubmitOrderVo submitOrderVo) {

        Order order = new Order();


        //雪花算法生成订单号
        String orderSn= IdWorker.getTimeId();
        order.setOrderSn(orderSn);
       // 获取当前用户的地址列表
        List<MemberReceiveAddressTO> addressList =getMemberAddress(getCurrentMember().getId());

        // 查找ID为addrId的地址
        MemberReceiveAddressTO address = addressList.stream()
                .filter(addr -> addr.getId().equals(submitOrderVo.getAddrId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Address not found for id: " + submitOrderVo.getAddrId()));

        //
        order.setMemberId(getCurrentMember().getId());
        order.setCouponId(14L);

        order.setCreateTime(new Date());

        order.setMemberUsername(getCurrentMember().getUsername());


        //地址
        order.setReceiverName(address.getName());
        order.setReceiverPhone(address.getPhone());
        order.setReceiverPostCode(address.getPostCode());
        order.setReceiverProvince(address.getProvince());
        order.setReceiverCity(address.getCity());
        order.setReceiverRegion(address.getRegion());
        order.setReceiverDetailAddress(address.getDetailAddress());

        //备注
        order.setNote(submitOrderVo.getRemark());
        //运费
        order.setFreightAmount(new BigDecimal(10));


        order.setStatus(OrderStatusEnum.CREATE_NEW.getCode());
        order.setAutoConfirmDay(7);

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

    private MemberTO getCurrentMember() {
        return LoginProtectedInterceptor.threadLocal.get();
    }

}