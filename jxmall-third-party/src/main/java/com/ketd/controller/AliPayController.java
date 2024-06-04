package com.ketd.controller;

import com.alipay.api.internal.util.AlipaySignature;

import com.ketd.common.domain.order.OrderTO;
import com.ketd.common.domain.order.PaymentInfoTO;
import com.ketd.common.enume.OrderStatusEnum;
import com.ketd.common.no_authentication_api.order.NoAuthenticationOrderOpenFeignApi;
import com.ketd.common.no_authentication_api.order.NoAuthenticationPaymentInfoOpenFeignApi;
import com.ketd.conf.AliPayConfig;
import com.ketd.domain.AliPayVo;
import com.ketd.domain.PayAsyncVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;


import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "jxmall-third-party/alipay")
public class AliPayController {

    @Autowired
    AliPayConfig aliPayConfig;

    @Autowired
    private NoAuthenticationOrderOpenFeignApi  noAuthenticationOrderOpenFeignApi;

    @Autowired
    private NoAuthenticationPaymentInfoOpenFeignApi noAuthenticationPaymentInfoOpenFeignApi;


    private static final String GATEWAY_URL ="https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    private static final String FORMAT ="JSON";
    private static final String CHARSET ="utf-8";
    private static final String SIGN_TYPE ="RSA2";
    @GetMapping("/pay")
    public void pay(@RequestParam(value = "orderId") Long orderId, HttpServletResponse httpResponse) throws Exception {

        AliPayVo aliPayVo = new AliPayVo();
        OrderTO order =  noAuthenticationOrderOpenFeignApi.getInfo(orderId).getData();
        aliPayVo.setOutTraceNo(order.getOrderSn());
        aliPayVo.setTotalAmount(order.getTotalAmount().setScale(2, java.math.RoundingMode.UP));
        aliPayVo.setSubject(order.getOrderTitle());

        PaymentInfoTO paymentInfoTO=new PaymentInfoTO();
        paymentInfoTO.setOrderId(order.getId());
        paymentInfoTO.setOrderSn(order.getOrderSn());
        paymentInfoTO.setCreateTime(new Date());
        noAuthenticationPaymentInfoOpenFeignApi.add(paymentInfoTO);


        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setReturnUrl(aliPayConfig.getReturnUrl());
        request.setBizContent("{\"out_trade_no\":\"" + aliPayVo.getOutTraceNo() + "\","
                + "\"total_amount\":\"" + aliPayVo.getTotalAmount() + "\","
                + "\"subject\":\"" + aliPayVo.getSubject() + "\","
                + "\"timeout_express\":\"" + "30m" + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            // 调用SDK生成表单
            form = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        // 直接将完整的表单html输出到页面
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @PostMapping("/notify")  // 注意这里必须是POST接口
    public String payNotify(PayAsyncVo payAsyncVo,HttpServletRequest request) throws Exception {

        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String o : requestParams.keySet()) {
            String[] values = (String[]) requestParams.get(o);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(o, valueStr);
        }
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        //支付宝交易号

        String trade_no = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params, aliPayConfig.getAlipayPublicKey(), CHARSET, "RSA2");

        handlePayResult(payAsyncVo, params);

        return  "success";

       /* if(verify_result){//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码

            handlePayResult(payAsyncVo, params);

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            } else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            }

            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——




            //////////////////////////////////////////////////////////////////////////////////////////
        }else{//验证失败

            return "fail";
        }*/



    }

    private void handlePayResult(PayAsyncVo payAsyncVo, Map<String, String> params) {
        // 验签通过
        System.out.println("交易名称: " + params.get("subject"));
        System.out.println("交易状态: " + params.get("trade_status"));
        System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
        System.out.println("商户订单号: " + params.get("out_trade_no"));
        System.out.println("交易金额: " + params.get("total_amount"));
        System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
        System.out.println("买家付款时间: " + params.get("gmt_payment"));
        System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

        PaymentInfoTO paymentInfoTO=noAuthenticationPaymentInfoOpenFeignApi.getInfoBySn(payAsyncVo.getOut_trade_no()).getData();
        paymentInfoTO.setAlipayTradeNo(payAsyncVo.getTrade_no());
        paymentInfoTO.setPaymentStatus(payAsyncVo.getTrade_status());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date notifyTime = formatter.parse(payAsyncVo.getNotify_time());
            paymentInfoTO.setCallbackTime(notifyTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        paymentInfoTO.setTotalAmount(payAsyncVo.getTotal_amount());
        paymentInfoTO.setSubject(payAsyncVo.getSubject());
        paymentInfoTO.setConfirmTime(new Date());
        paymentInfoTO.setCallbackTime(new Date());

        noAuthenticationPaymentInfoOpenFeignApi.edit(paymentInfoTO);

        if(paymentInfoTO.getPaymentStatus().equals("TRADE_SUCCESS")||paymentInfoTO.getPaymentStatus().equals("TRADE_FINISHED")){
            noAuthenticationOrderOpenFeignApi.updateStatusByOrderSn(OrderStatusEnum.PAYED.getCode(), payAsyncVo.getOut_trade_no());

        }
    }
}
