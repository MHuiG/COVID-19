package com.cov.entity;

import java.util.List;

public class ListEntity {
    String name;
    List list;

    public ListEntity(String name, List list) {
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
