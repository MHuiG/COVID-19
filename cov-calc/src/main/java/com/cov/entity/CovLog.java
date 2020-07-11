package com.cov.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class CovLog implements Serializable{
    private String  id;
    private String  name;
    private ArrayList<Log>  series;

    public static CovLog Str2Bean(String Str) {
        CovLog covLog = new CovLog();
        try {
            JSONObject jsonObj = new JSONObject(Str);
            String  id = jsonObj.getString("id");
            String  name = jsonObj.getString("name");
            JSONArray series =jsonObj.getJSONArray("series");
            ArrayList<Log> logs=new ArrayList<>();

            for (int i = 0; i < series.length(); i++) {
                JSONObject one=series.getJSONObject(i);
                Log log=new Log();
                log.setDate(one.getString("date"));
                log.setDeathsRatio(one.getDouble("deathsRatio"));
                log.setCuresNum(one.getInt("curesNum"));
                log.setConfirmedIncr(one.getInt("confirmedIncr"));
                log.setConfirmedNum(one.getInt("confirmedNum"));
                log.setDeathsNum(one.getInt("deathsNum"));
                log.setConfirmedIncr(one.getInt("asymptomaticIncr"));
                log.setCuresNum(one.getInt("curesRatio"));
                log.setTreatingNum(one.getInt("treatingNum"));
                log.setAsymptomaticNum(one.getInt("asymptomaticNum"));
                logs.add(log);
            }

            covLog.setId(id);
            covLog.setName(name);
            covLog.setSeries(logs);
        } catch (Exception e) {
            return null;
        }
        return covLog;
    }

    @Override
    public String toString() {
        return "covLog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", series='" + series.toString() + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Log> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<Log> series) {
        this.series = series;
    }
}
