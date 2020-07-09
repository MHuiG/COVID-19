package com.cov.service.impl;

import com.cov.service.CovService;
import org.springframework.stereotype.Service;

@Service
public class CovServiceImpl implements CovService {
    @Override
    public String test() {
        return "1";
    }
}
