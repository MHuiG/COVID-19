package com.cov.entity;

import org.json.JSONObject;

import java.io.Serializable;

public class VehicleLog implements Serializable{
    private String did;

    public static VehicleLog json2Bean(String jsonStr) {
        VehicleLog vehicleLog = new VehicleLog();
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
