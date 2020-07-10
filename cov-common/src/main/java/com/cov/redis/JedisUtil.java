package com.cov.redis;

import com.cov.PropertiesUtil;
import com.cov.conf.ConfigurationManager;
import com.cov.conf.Constants;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * redis的工具类
 */
public class JedisUtil {
    private JedisUtil(){}

    private static JedisPool pool;
    static {
        JedisPoolConfig jedisConfig = new JedisPoolConfig();
        String host = ConfigurationManager.getProperty(Constants.JEDIS_HOST);
        int port = ConfigurationManager.getIntegerProperty(Constants.JEDIS_PORT);
        pool = new JedisPool(jedisConfig, host, port);
    }
    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void release(Jedis jedis) {
        jedis.close();
    }

    public static String getJedisProperty(String field) {
        return PropertiesUtil.getStringByKey("default.properties", field);
    }
    public static void main(String[] args) {

    }
}
