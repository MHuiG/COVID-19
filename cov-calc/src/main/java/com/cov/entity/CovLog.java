package com.cov.entity;

import java.io.Serializable;

public class CovLog implements Serializable{
    private String  date;
    private String  state;
    private String  fips;
    private int  cases;
    private int  deaths;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFips() {
        return fips;
    }

    public void setFips(String fips) {
        this.fips = fips;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public static CovLog Str2Bean(String Str) {
        CovLog covLog = new CovLog();
        try {
            String[] a = Str.split(",");
            String  date=a[0];
            String  state=a[1];
            String  fips=a[2];
            int cases= Integer.valueOf(a[3]);
            int deaths=Integer.valueOf(a[4]);
            covLog.setDate(date);
            covLog.setState(state);
            covLog.setFips(fips);
            covLog.setCases(cases);
            covLog.setDeaths(deaths);
        } catch (Exception e) {
            return null;
        }
        return covLog;
    }

    @Override
    public String toString() {
        return "covLog{" +
                "date='" + date + '\'' +
                ", state='" + state + '\'' +
                ", fips='" + fips + '\'' +
                ", cases='" + cases + '\'' +
                ", deaths='" + deaths + '\'' +
                '}';
    }
}
