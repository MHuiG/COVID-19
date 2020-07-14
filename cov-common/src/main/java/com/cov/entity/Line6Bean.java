package com.cov.entity;

import java.io.Serializable;

public class Line6Bean implements Serializable {
    String date;
    String type;
    int num;


    @Override
    public String toString() {
        return "Line6Bean{" +
                "date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", num=" + num +
                '}';
    }

    public Line6Bean(String date, String type, int num) {
        this.date = date;
        this.type = type;
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
