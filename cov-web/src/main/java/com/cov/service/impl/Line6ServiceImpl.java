package com.cov.service.impl;

import com.cov.entity.LineBean;
import com.cov.redis.JedisUtil;
import com.cov.service.Line6Service;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Map;

@Service
public class Line6ServiceImpl implements Line6Service {
    @Override
    public String test() {
        Jedis jedis = JedisUtil.getJedis();
        Map<String, String> data = jedis.hgetAll("cov");
        JedisUtil.release(jedis);
        LineBean lineBean = new LineBean(data.get("line6_date"), data.get("line6_confirmedNum"), data.get("line6_curesNum"), data.get("line6_deathsNum"), data.get("line6_treatingNum"));
        JSONObject jsonObj = new JSONObject(lineBean);
        return jsonObj.toString();
    }
}
