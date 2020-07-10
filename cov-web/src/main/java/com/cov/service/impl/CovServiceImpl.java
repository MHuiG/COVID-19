package com.cov.service.impl;

import com.cov.redis.JedisUtil;
import com.cov.service.CovService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class CovServiceImpl implements CovService {
    @Override
    public String test() {
        Jedis jedis = JedisUtil.getJedis();

        JedisUtil.release(jedis);
        return "1";
    }
}
