package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.springboot.dao.dao;

public class FacilityServiceImpl implements FacilityService {

    @Autowired
    dao daoInterface;
   
    @Override
    public boolean saveEntity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveEntity'");
    }
}
