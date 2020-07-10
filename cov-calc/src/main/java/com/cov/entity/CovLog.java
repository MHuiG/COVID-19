package com.cov.entity;

import org.json.JSONObject;

import java.io.Serializable;

public class CovLog implements Serializable{
    private String did;

    public static CovLog json2Bean(String jsonStr) {
        CovLog vehicleLog = new CovLog();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);

        } catch (Exception e) {
            return null;
        }
        return vehicleLog;
    }

    @Override
    public String toString() {
        return "Log{" +

                '}';
    }
}
