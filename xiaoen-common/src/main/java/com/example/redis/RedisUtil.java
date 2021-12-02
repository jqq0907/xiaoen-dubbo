package com.example.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangqiangqiang
 * @description: redis工具
 * @date 2021/11/20 4:56 下午
 */
@Component
public class RedisUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    private RedisTemplate<Object, Object> redisTemplate;

    public RedisUtil(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 普通获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }


    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return /
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 设置过期时间
     *
     * @param key  键
     * @param time 过期时间(秒)
     * @return /
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    /**
     * 设置过期时间
     *
     * @param key      键
     * @param time     过期时间
     * @param timeUnit 时间单位
     * @return /
     */
    public boolean expire(String key, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, timeUnit);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    /**
     * 设置缓存(不过期)
     *
     * @param key   键
     * @param value 值
     * @return /
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    /**
     * 设置缓存
     *
     * @param key   键
     * @param value 值
     * @param time  过期时间(秒)
     * @return /
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    /**
     * 删除缓存
     *
     * @param keys 键,一个或多个
     */
    public void del(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                Boolean result = redisTemplate.delete(keys[0]);
                logger.debug("删除缓存: key={}{}", keys[0], result ? "成功" : "失败");
            } else {
                Long count = redisTemplate.delete(Arrays.asList(keys));
                logger.debug("成功删除{}个缓存: {}", count, Arrays.toString(keys));
            }
        }
    }
}
