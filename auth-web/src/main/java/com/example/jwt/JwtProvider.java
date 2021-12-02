package com.example.jwt;

import cn.hutool.core.util.IdUtil;
import com.example.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

/**
 * @author jiangqiangqiang
 * @Description:
 * @date 2021/10/27 5:39 下午
 */
@Component
@Scope("singleton")
public class JwtProvider {
    @Resource
    private JwtProperties jwtProperties;

    public JwtProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 解析token
     *
     * @param token /
     * @return /
     */
    public String parseToken(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * 生成token
     *
     * @param username /
     * @return /
     */
    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setId(IdUtil.simpleUUID())
                .setIssuedAt(now)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .compact();
    }

    /**
     * 获取claims
     *
     * @param token /
     * @return /
     */
    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(token).getBody();
    }

    /**
     * 获取创建token时间
     *
     * @param token          /
     * @param claimsResolver /
     * @return /
     */
    public Date getExpirationDate(String token, Function<Claims, Date> claimsResolver) {
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 判断是否过期
     *
     * @param token /
     * @return /
     */
    public boolean isExpired(String token) {
        try {
            Date expirationDate = getExpirationDate(token, Claims::getExpiration);
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    /**
     * 生成加密key
     *
     * @return /
     */
    public SecretKey generateKey() {
        byte[] decode = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(decode);
    }
}
