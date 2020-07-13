package com.cov.entity;

public class MapBean {
    private String name;
    private int confirmedNum;
    private int curesNum;
    private int deathsNum;
    private double curesRatio;
    private double deathsRatio;
    private int treatingNum;
    private int confirmedIncr;
    private int asymptomaticNum;
    private int asymptomaticIncr;

    public MapBean(String name, int confirmedNum, int curesNum, int deathsNum, double curesRatio, double deathsRatio, int treatingNum, int confirmedIncr, int asymptomaticNum, int asymptomaticIncr) {
        this.name = name;
        this.confirmedNum = confirmedNum;
        this.curesNum = curesNum;
        this.deathsNum = deathsNum;
        this.curesRatio = curesRatio;
        this.deathsRatio = deathsRatio;
        this.treatingNum = treatingNum;
        this.confirmedIncr = confirmedIncr;
        this.asymptomaticNum = asymptomaticNum;
        this.asymptomaticIncr = asymptomaticIncr;
    }

    @Override
    public String toString() {
        return "MapBean{" +
                "name='" + name + '\'' +
                ", confirmedNum=" + confirmedNum +
                ", curesNum=" + curesNum +
                ", deathsNum=" + deathsNum +
                ", curesRatio=" + curesRatio +
                ", deathsRatio=" + deathsRatio +
                ", treatingNum=" + treatingNum +
                ", confirmedIncr=" + confirmedIncr +
                ", asymptomaticNum=" + asymptomaticNum +
                ", asymptomaticIncr=" + asymptomaticIncr +
                '}';
    }
//    @Override
//    public String toString() {
//        return "{" +
//                "\"name\":\"" + name + '\"' +
//                ", \"confirmedNum\":" + confirmedNum +
//                ", \"curesNum\":" + curesNum +
//                ", \"deathsNum\":" + deathsNum +
//                ", \"curesRatio\":" + curesRatio +
//                ", \"deathsRatio\":" + deathsRatio +
//                ", \"treatingNum\":" + treatingNum +
//                ", \"confirmedIncr\":" + confirmedIncr +
//                ", \"asymptomaticNum\":" + asymptomaticNum +
//                ", \"asymptomaticIncr\":" + asymptomaticIncr +
//                '}';
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConfirmedNum() {
        return confirmedNum;
    }

    public void setConfirmedNum(int confirmedNum) {
        this.confirmedNum = confirmedNum;
    }

    public int getCuresNum() {
        return curesNum;
    }

    public void setCuresNum(int curesNum) {
        this.curesNum = curesNum;
    }

    public int getDeathsNum() {
        return deathsNum;
    }

    public void setDeathsNum(int deathsNum) {
        this.deathsNum = deathsNum;
    }

    public double getCuresRatio() {
        return curesRatio;
    }

    public void setCuresRatio(double curesRatio) {
        this.curesRatio = curesRatio;
    }

    public double getDeathsRatio() {
        return deathsRatio;
    }

    public void setDeathsRatio(double deathsRatio) {
        this.deathsRatio = deathsRatio;
    }

    public int getTreatingNum() {
        return treatingNum;
    }

    public void setTreatingNum(int treatingNum) {
        this.treatingNum = treatingNum;
    }

    public int getConfirmedIncr() {
        return confirmedIncr;
    }

    public void setConfirmedIncr(int confirmedIncr) {
        this.confirmedIncr = confirmedIncr;
    }

    public int getAsymptomaticNum() {
        return asymptomaticNum;
    }

    public void setAsymptomaticNum(int asymptomaticNum) {
        this.asymptomaticNum = asymptomaticNum;
    }

    public int getAsymptomaticIncr() {
        return asymptomaticIncr;
    }

    public void setAsymptomaticIncr(int asymptomaticIncr) {
        this.asymptomaticIncr = asymptomaticIncr;
    }
}
