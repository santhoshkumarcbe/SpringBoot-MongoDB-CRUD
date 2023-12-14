package com.example.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Entity;

import com.example.springboot.dao.dao;
import com.example.springboot.repository.FacilityRepository;

public class FacilityServiceImpl implements FacilityService{

    @Autowired
    private FacilityRepository entityRepository;
    @Autowired
    dao daoInterface;
   
    @Override
    public boolean saveEntity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveEntity'");
    }
  
    
}
