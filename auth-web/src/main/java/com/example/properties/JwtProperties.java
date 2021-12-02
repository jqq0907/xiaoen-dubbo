package com.example.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author jiangqiangqiang
 * @Description:
 * @date 2021/10/27 8:15 下午
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * 头
     */
    private String header;

    /**
     * 令牌前缀 Bearer
     */
    private String tokenStartWith;

    /**
     * 用于编码
     */
    private String secretKey;

    /**
     * 过期时间
     */
    private Integer expiration;
}
