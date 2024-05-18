package com.ketd.auth.server.impl;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ketd.auth.constant.TypeConstant;
import com.ketd.auth.server.OAuth2Service;
import com.ketd.auth.util.JXJwtTokenUtil;
import com.ketd.auth.vo.MemberInfoVo;
import com.ketd.common.api.member.MemberOpenFeignApi;
import com.ketd.common.api.member.MemberSocialOpenFeignApi;
import com.ketd.common.domain.member.MemberSocialTO;
import com.ketd.common.domain.member.MemberTO;
import com.ketd.common.result.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class OAuth2ServiceImpl implements OAuth2Service {

    @Autowired
    private MemberSocialOpenFeignApi memberSocialOpenFeignApi;

    @Autowired
    private MemberOpenFeignApi memberOpenFeignApi;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JXJwtTokenUtil jwtTokenUtil;

    @Value("${OAuth2.github.client_id}")
    private String githubClientId;

    @Value("${OAuth2.github.client_secret}")
    private String githubClientSecret;

    @Value("${OAuth2.gitee.client_id}")
    private String giteeClientId;

    @Value("${OAuth2.gitee.client_secret}")
    private String giteeClientSecret;

    @Value("${OAuth2.gitee.redirect_uri}")
    private String giteeRedirectUri;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Override
    public Result<?> github(String code) {
        return processOAuth("https://github.com/login/oauth/access_token", "https://api.github.com/user/repos", code,
                githubClientId, githubClientSecret, TypeConstant.GIT_HUB_TYPE);
    }

    @Override
    public Result<?> gitee(String code) {
        return processOAuth("https://gitee.com/oauth/token", "https://gitee.com/api/v5/user", code,
                giteeClientId, giteeClientSecret, TypeConstant.GITEE_TYPE);
    }

    private Result<?> processOAuth(String tokenUrl, String userUrl, String code, String clientId, String clientSecret, int type) {


        Map<String, Object> param = new HashMap<>();
        param.put("client_id", clientId);
        param.put("client_secret", clientSecret);
        param.put("code", code);

        HttpResponse httpResponse;

        if(type == TypeConstant.GITEE_TYPE){
            param.put("grant_type", "authorization_code");
            param.put("redirect_uri", giteeRedirectUri);
            httpResponse = HttpUtil.createPost(tokenUrl)
                    .form(param)
                    .execute();
        }else{
            httpResponse = HttpUtil.createGet(tokenUrl)
                    .form(param)
                    .execute();
        }




        if (httpResponse.getStatus() != 200) {
            return Result.error("Failed to retrieve access token");
        }

        String responseBody = httpResponse.body();
        String access_token;
        String expires_in;
        if (type == TypeConstant.GITEE_TYPE) {
            JSONObject responseBodyToJson = JSON.parseObject(responseBody);
            access_token = responseBodyToJson.getString("access_token");
            expires_in = responseBodyToJson.getString("expires_in");
        }else{
            Map<String, String> responseMap = HttpUtil.decodeParamMap(responseBody, StandardCharsets.UTF_8);
            access_token = responseMap.get("access_token");
            expires_in = responseMap.get("expires_in");
        }




        if (access_token == null) {
            return Result.error("Failed to retrieve access token");
        }


        HttpResponse getUserResponse;

        if(type == TypeConstant.GITEE_TYPE){
            // 发送 GET 请求获取用户信息
            getUserResponse = HttpUtil.createGet(userUrl)
                    .form("access_token", access_token)
                    .execute();
        }else{
            getUserResponse = HttpUtil.createGet(userUrl)
                    .header("Authorization", "token " + access_token)
                    .execute();
        }


        if (getUserResponse.getStatus() != 200) {
            return Result.error("Failed to retrieve user information");
        }
        System.out.println(getUserResponse);

        JSONObject jsonUserResponse;
        if (type == TypeConstant.GITEE_TYPE){
            jsonUserResponse = JSON.parseObject(getUserResponse.body());
        }else{
            JSONArray jsonArray = JSON.parseArray(getUserResponse.body());
            jsonUserResponse = jsonArray.getJSONObject(0);
        }

        String socialUid = jsonUserResponse.getString("id");

        MemberSocialTO memberSocialTO = memberSocialOpenFeignApi.getInfoBySocialUid(socialUid, type).getData();
        if (memberSocialTO != null) {
            return handleExistingUser(memberSocialTO.getMemberId());
        } else {
            return handleNewUser(jsonUserResponse, access_token, expires_in, type);
        }
    }

    private Result<?> handleExistingUser(Long memberId) {
        Result<MemberTO> result = memberOpenFeignApi.getInfo(memberId);
        MemberTO memberTO = result.getData();
        if (memberTO != null && result.getCode() == 200) {
            MemberInfoVo memberInfoVo = new MemberInfoVo();
            BeanUtils.copyProperties(memberTO, memberInfoVo);

            String token = jwtTokenUtil.generatorToken(memberInfoVo.getId());
            memberInfoVo.setToken(token);

            String redisKey = "jxmall:auth:token-" + memberInfoVo.getId();
            redisUtil.setJson(redisKey, token, expiration);

            return Result.ok(memberInfoVo);
        } else {
            return Result.error(null);
        }
    }

    /*TODO 这里的逻辑有点问题，需要重构*/
    private Result<?> handleNewUser(JSONObject jsonUserResponse, String accessToken, String expiresIn, int type) {
        MemberTO member = new MemberTO();
        member.setLevelId(1L);
        member.setUsername(jsonUserResponse.getString("name"));
        member.setNickname(member.getUsername());
        member.setCreateTime(new Date());

        MemberTO memberTO = memberOpenFeignApi.add(member).getData();

        MemberSocialTO memberSocial = new MemberSocialTO();
        memberSocial.setMemberId(memberTO.getId());
        memberSocial.setType(type);
        memberSocial.setSocialUid(jsonUserResponse.getString("id"));
        memberSocial.setAccessToken(accessToken);
        memberSocial.setExporesIn(Long.valueOf(expiresIn));
        memberSocialOpenFeignApi.add(memberSocial);

        MemberInfoVo memberInfoVo = new MemberInfoVo();
        BeanUtils.copyProperties(memberTO, memberInfoVo);

        String token = jwtTokenUtil.generatorToken(memberInfoVo.getId());
        memberInfoVo.setToken(token);

        String redisKey = "jxmall:auth:token:" + memberInfoVo.getId();
        redisUtil.setJson(redisKey, token, expiration);

        return Result.ok(memberInfoVo);
    }
}
