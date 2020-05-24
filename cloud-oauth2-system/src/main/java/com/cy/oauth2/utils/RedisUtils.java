package com.cy.oauth2.utils;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis操作类
 */
@Component
public class RedisUtils {
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 维护一个本类的静态变量
    private static RedisUtils redisUtils;

    @PostConstruct
    public void init() {
        redisUtils = this;
        redisUtils.redisTemplate = this.redisTemplate;
    }

    /**
     * 将参数中的字符串值设置为键的值，不设置过期时间
     * @param key
     * @param value 必须要实现 Serializable 接口
     */
    public static void set(String key, String value) {
        redisUtils.redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 将参数中的字符串值设置为键的值，设置过期时间
     * @param key
     * @param value 必须要实现 Serializable 接口
     * @param timeout
     */
    public static void set(String key, String value, Long timeout) {
        redisUtils.redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取与指定键相关的值
     * @param key
     * @return
     */
    public static Object get(String key) {
        return redisUtils.redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置某个键的过期时间
     * @param key 键值
     * @param ttl 过期秒数
     */
    public static boolean expire(String key, Long ttl) {
        return redisUtils.redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
    }

    /**
     * 判断某个键是否存在
     * @param key 键值
     */
    public static boolean hasKey(String key) {
        return redisUtils.redisTemplate.hasKey(key);
    }

    /**
     * 向集合添加元素
     * @param key
     * @param value
     * @return 返回值为设置成功的value数
     */
    public static Long sAdd(String key, String... value) {
        return redisUtils.redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 获取集合中的某个元素
     * @param key
     * @return 返回值为redis中键值为key的value的Set集合
     */
    public static Set<String> sGetMembers(String key) {
        return redisUtils.redisTemplate.opsForSet().members(key);
    }

    /**
     * 将给定分数的指定成员添加到键中存储的排序集合中
     * @param key
     * @param value
     * @param score
     * @return
     */
    public static Boolean zAdd(String key, String value, double score) {
        return redisUtils.redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 返回指定排序集中给定成员的分数
     * @param key
     * @param value
     * @return
     */
    public static Double zScore(String key, String value) {
        return redisUtils.redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 删除指定的键
     * @param key
     * @return
     */
    public static Boolean delete(String key) {
        return redisUtils.redisTemplate.delete(key);
    }

    /**
     * 删除多个键
     * @param keys
     * @return
     */
    public static Long delete(Collection<String> keys) {
        return redisUtils.redisTemplate.delete(keys);
    }
}
