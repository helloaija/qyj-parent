package com.qyj.web.common.utils;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.ContextLoader;

/**
 * redis工具类
 * @author CTF_stone
 *
 */
public class RedisUtil {
	
	private static StringRedisTemplate redisTemplate = (StringRedisTemplate) ContextLoader.getCurrentWebApplicationContext().getBean("redisTemplate");;
	
	/**
	 * 保存字符串
	 * @param key
	 * @param value
	 */
	public static void setString(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}
	
	/**
	 * 保存字符串，并设置过期时间
	 * @param key
	 * @param value
	 * @param timeout 过期时间
	 * @param timeUnit 时间单位
	 */
	public static void setStringEx(String key, String value, Long timeout, TimeUnit timeUnit) {
		redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
	}
	
	/**
	 * 保存字符串，并设置过期时间（单位：秒）
	 * @param key
	 * @param value
	 * @param timeout 过期时间
	 */
	public static void setStringEx(String key, String value, Long timeout) {
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}
	
	/**
	 * 获取保存的字符串
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * 根据key删除保存的value
	 * @param key
	 */
	public static void del(String key) {
		redisTemplate.delete(key);
	}
	
	/**
	 * 保存对象
	 * @param key
	 * @param value
	 */
	public static void setObject(String key, Object value) {
		redisTemplate.opsForValue().set(key, SerializeUtil.serialize(value));
	}
	
	/**
	 * 保存对象，并设置过期时间
	 * @param key
	 * @param value
	 * @param timeout 过期时间
	 * @param timeUnit 时间单位
	 */
	public static void setObjectEx(String key, Object value, Long timeout, TimeUnit timeUnit) {
		redisTemplate.opsForValue().set(key, SerializeUtil.serialize(value), timeout, timeUnit);
	}
	
	/**
	 * 保存字对象，并设置过期时间（单位：秒）
	 * @param key
	 * @param value
	 * @param timeout 过期时间
	 */
	public static void setObjectEx(String key, Object value, Long timeout) {
		redisTemplate.opsForValue().set(key, SerializeUtil.serialize(value), timeout, TimeUnit.SECONDS);
	}
	
	/**
	 * 获取保存的对象
	 * @param key
	 * @return
	 */
	public static Object getObject(String key) {
		return SerializeUtil.unserialize(redisTemplate.opsForValue().get(key));
	}
}
