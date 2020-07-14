package com.cov.sort;

import com.cov.entity.Line6Bean;

import java.util.Comparator;

public class SortClass implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Line6Bean l1 = (Line6Bean) o1;
        Line6Bean l2 = (Line6Bean) o2;
        int flag = l1.getDate().compareTo(l2.getDate());
        return flag;
    }
}
