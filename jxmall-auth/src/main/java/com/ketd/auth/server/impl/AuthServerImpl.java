package com.ketd.auth.server.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.ketd.auth.dto.EmailDto;
import com.ketd.auth.server.AuthServer;
import com.ketd.auth.server.EmailService;
import com.ketd.auth.util.RedisUtil;
import com.ketd.auth.vo.LoginVo;
import com.ketd.auth.vo.MemberVo;
import com.ketd.common.api.coupon.MemberPriceOpenFeignApi;
import com.ketd.common.api.member.MemberOpenFeignApi;
import com.ketd.common.domain.member.MemberTO;
import com.ketd.common.result.Result;
import com.ketd.common.result.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.auth.server.impl
 * @Author: ketd
 * @CreateTime: 2024-05-15  15:36
 */
@Service
@Primary
public class AuthServerImpl implements AuthServer {

    @Autowired
    private EmailService emailService;

    @Autowired
    private MemberOpenFeignApi  memberOpenFeignApi;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;



    @Autowired
    private RedisUtil redisUtil;



    // 验证码放入redis缓存过期时间
    @Value("${code.expiration}")
    private Long expiration;

    @Override
    public Result<?> register(MemberVo memberVo) {


        if (memberOpenFeignApi.getInfoByMobile(memberVo.getMobile()).getData() !=null) {
            return Result.build(null, ResultCodeEnum.USER_MOBILE_EXISTENCE);
        }
        if(memberOpenFeignApi.getInfoByEmail(memberVo.getEmail()).getData() !=null){
            return Result.build(null, ResultCodeEnum.EMAIL_EXISTENCE);
        }

        // 通过email获取redis中的code
        Object value = redisUtil.get("regist-Code:"+"Email-"+memberVo.getEmail());
        if (value == null || !value.toString().equals(memberVo.getCode())) {

            return Result.build(null, ResultCodeEnum.INVALID_VERIFICATION_CODE);
        } else {
            redisUtil.del("regist-Code:"+"Email-"+memberVo.getEmail());
        }

        MemberTO member = new MemberTO();
        member.setLevelId(1L);
        member.setUsername(memberVo.getUsername());
        member.setNickname(memberVo.getNickname());
        member.setMobile(memberVo.getMobile());
        member.setEmail(memberVo.getEmail());
        member.setHeader(null);
        member.setCreateTime(new Date());




        //加密
        String encodedPassword = BCrypt.hashpw( memberVo.getPassword(), BCrypt.gensalt());
        member.setPassword(encodedPassword);
        memberOpenFeignApi.add(member);
        return Result.build(null, ResultCodeEnum.REGISTER_SUCCESS);

    }

    @Override
    public Result<?> sendMailCode(String email) {

        // 查看注册邮箱是否存在
        if (memberOpenFeignApi.getInfoByEmail(email).getData() != null) {
            return Result.build(null, ResultCodeEnum.EMAIL_EXISTENCE);
        }

        // 获取发送邮箱验证码的HTML模板
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email-code.ftl");

        // 从redis缓存中尝试获取验证码
        String code = redisUtil.get("regist-Code:" + "Email-" + email);
        if (code == null) {
            // 如果在缓存中未获取到验证码，则产生6位随机数，放入缓存中
            code = RandomUtil.randomNumbers(6);
            redisUtil.set("regist-Code:" + "Email-" + email, code, expiration);
        }

        // 创建一个CompletableFuture用于传递验证码
        CompletableFuture<String> codeFuture = CompletableFuture.completedFuture(code);

        codeFuture.thenComposeAsync(c -> {
            emailService.send(new EmailDto(Collections.singletonList(email),
                    "京西商城||邮箱验证码", template.render(Dict.create().set("code", c))));
            return null;
        }, threadPoolExecutor);
        // 使用supplyAsync处理异步发送邮件操作
        return Result.ok(null);
    }

    @Override
    public Result<?> login(LoginVo loginVo) {
        MemberTO  foundUser=null;
        if(loginVo.getEmail()==null|| loginVo.getEmail().isEmpty()){
            foundUser = memberOpenFeignApi.getInfoByMobile(loginVo.getMobile()).getData();
        }
        if(loginVo.getMobile()==null|| loginVo.getMobile().isEmpty()){
            foundUser = memberOpenFeignApi.getInfoByEmail(loginVo.getEmail()).getData();

        }
        if(foundUser==null){
            // 登录失败
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
        boolean flag = BCrypt.checkpw(loginVo.getPassword(), foundUser.getPassword());
        if (flag) {

    /*        String token=jwtHelper.createToken(foundUser.getUserId());
            redisUtil.set(COOKIE_NAME_TOKEN + ":" + "user-"+foundUser.getUserId(), token, TOKEN_EXPIRE);

*/

            HashMap<String, String> data = new HashMap<>();
            data.put("token", "token");

            // 登录成功
            return Result.build(data,ResultCodeEnum.LOGIN_SUCCESS);
        } else {
            // 登录失败
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
    }

}
