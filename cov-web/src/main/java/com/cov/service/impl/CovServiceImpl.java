package com.cov.service.impl;

import com.cov.redis.JedisUtil;
import com.cov.service.CovService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Map;

@Service
public class CovServiceImpl implements CovService {
    @Override
    public String test() {
        Jedis jedis = JedisUtil.getJedis();
        Map<String, String> city_data = jedis.hgetAll("cov");
        JedisUtil.release(jedis);
//        System.out.println(city_data.get("map"));
        return city_data.get("map");
    }
}
