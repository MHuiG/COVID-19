package com.cov.service.impl;


import com.cov.redis.JedisUtil;
import com.cov.service.Bar2Service;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Map;

@Service
public class Bar2ServiceImpl implements Bar2Service {
    @Override
    public String get_data() {
        Jedis jedis = JedisUtil.getJedis();
        Map<String, String> city_data = jedis.hgetAll("cov");
        JedisUtil.release(jedis);
//        System.out.println(city_data.get("bar2"));
        return city_data.get("bar2");
    }
}
