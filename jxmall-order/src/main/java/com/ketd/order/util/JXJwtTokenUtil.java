package com.ketd.order.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.common.utils
 * @Author: ketd
 * @CreateTime: 2024-05-17  18:19
 */
@Service
public class JXJwtTokenUtil
{
    //过期时间
    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.key}")
    private String key;

    /**
     * 1.从本地文件读取秘钥
     * 2.工程.yml中配置salt
     * 3.最终生成jwt秘钥，秘钥组成：MD5（1+2）
     */
    //private final SecretKey secret=Keys.hmacShaKeyFor(DigestUtils.md5Hex(key).getBytes());
    /**
     * 初始化负载内数据
     * @param username 用户名
     * @return 负载集合
     */
    private Map<String,Object> initClaims(String username){
        Map<String, Object> claims = new HashMap<>();
        //"iss" (Issuer): 代表 JWT 的签发者。在这个字段中填入一个字符串，表示该 JWT 是由谁签发的。例如，可以填入你的应用程序的名称或标识符。
        claims.put("iss","jx");
        //"sub" (Subject): 代表 JWT 的主题，即该 JWT 所面向的用户。可以是用户的唯一标识符或者其他相关信息。
        claims.put("sub",username);
        //"exp" (Expiration Time): 代表 JWT 的过期时间。通常以 UNIX 时间戳表示，表示在这个时间之后该 JWT 将会过期。建议设定一个未来的时间点以保证 JWT 的有效性，比如一个小时、一天、一个月后的时间。
        claims.put("exp",generatorExpirationDate());
        //"aud" (Audience): 代表 JWT 的接收者。这个字段可以填入该 JWT 预期的接收者，可以是单个用户、一组用户、或者某个服务。
        claims.put("aud","internal use");
        //"iat" (Issued At): 代表 JWT 的签发时间。同样使用 UNIX 时间戳表示。
        claims.put("iat",new Date());
        //"jti" (JWT ID): JWT 的唯一标识符。这个字段可以用来标识 JWT 的唯一性，避免重放攻击等问题。
        claims.put("jti", UUID.randomUUID().toString());
        //"nbf" (Not Before): 代表 JWT 的生效时间。在这个时间之前 JWT 不会生效，通常也是一个 UNIX 时间戳。我这里不填，没这个需求
        return claims;
    }

    /**
     * 根据用户信息生成token
     *
     * @param userId 用户Id
     * @return token
     */
    public String generatorToken(Long userId)
    {
        Map<String, Object> claims = initClaims(String.valueOf(userId));
        return generatorToken(claims);
    }

    /**
     * 根据负载生成JWT token
     * @param claims 负载
     * @return token
     */
    private String generatorToken(Map<String,Object> claims){
        return Jwts.builder()
                .claims(claims)
                .signWith(Keys.hmacShaKeyFor(key.getBytes()),Jwts.SIG.HS256)
                .compact();
    }

    /**
     * 生成失效时间，以秒为单位
     *
     * @return 预计失效时间
     */
    private Date generatorExpirationDate()
    {
        //预计失效时间为：token生成时间+预设期间
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从Token中获取用户名
     * @param token token
     * @return 用户id
     */
    public Long getUserIdFromToken(String token){
        Long userId;
        try
        {
            userId = Long.valueOf(getPayloadFromToken(token).getSubject());
        }catch (Exception e){
            userId = null;
        }
        return userId;
    }

    /**
     * 从Token中获取负载中的Claims
     * @param token token
     * @return 负载
     */
    private Claims getPayloadFromToken(String token)
    {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(key.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 验证token是否有效
     * @param token 需要被验证的token
     * @param userId 用户Id
     * @return
     */
    public boolean validateToken(String token,Long userId){
        return getUserIdFromToken(token).equals(String.valueOf(userId)) && !isTokenExpired(token);
    }

    /**
     * 判断token是否有过期
     * @param token 需要被验证的token
     * @return true/false
     */
    public boolean isTokenExpired(String token)
    {
        //判断预设时间是否在当前时间之前，如果在当前时间之前，就表示过期了，会返回true
        return getExpiredDateFromToken(token).before(new Date());
    }

    /**
     * 从token中获取预设的过期时间
     * @param token token
     * @return 预设的过期时间
     */
    private Date getExpiredDateFromToken(String token)
    {
        return getPayloadFromToken(token).getExpiration();
    }

    /**
     * 判断token是否可以被刷新
     * @param token 需要被验证的token
     * @return true/false
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token 需要被刷新的token
     * @return 刷新后的token
     */
    public String refreshToken(String token){
        Claims claims = getPayloadFromToken(token);
        Map<String, Object> initClaims = initClaims(claims.getSubject());
        initClaims.put("iat",new Date());
        return generatorToken(initClaims);
    }
}

