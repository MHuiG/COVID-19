package com.cov.service.impl;

import com.cov.entity.MapBean;
import com.cov.redis.JedisUtil;
import com.cov.service.CovService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Map;

@Service
public class CovServiceImpl implements CovService {
    /*
    ncovcity_data
    ncov_data
    trend
     */
    @Override
    public String test() {
//        Jedis jedis = JedisUtil.getJedis();
//        Map<String, String> city_data = jedis.hgetAll("ncovcity_data");
//        JSONObject json = new JSONObject();
//        JedisUtil.release(jedis);

        MapBean mapBean=new MapBean("Wisconsin", 55205, 2452, 52, 542, 22, 252, 425, 25, 25) ;
        MapBean mapBean2=new MapBean("Wyoming", 55205, 2452, 52, 542, 22, 252, 425, 25, 25) ;
        MapBean mapBean3=new MapBean("New York", 55205, 2452, 52, 542, 22, 252, 425, 25, 25) ;
        JSONObject jsonObj = new JSONObject(mapBean);
        JSONObject jsonObj2 = new JSONObject(mapBean2);
        JSONObject jsonObj3 = new JSONObject(mapBean3);
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        jsonObjects.add(jsonObj);
        jsonObjects.add(jsonObj2);
        jsonObjects.add(jsonObj3);
            return jsonObjects.toString();
    }
}
