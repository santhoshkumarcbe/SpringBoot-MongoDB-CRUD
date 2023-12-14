package com.example.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.dao.dao;
import com.example.springboot.entity.RawMaterial;
import com.example.springboot.errorHandling.rawMaterialNotFoundException;
import com.example.springboot.repository.RawMaterialRepository;

@Service
public class RawMaterialServiceImpl implements RawMaterialService{

    @Autowired
    private RawMaterialRepository rawMaterialRepository;
    @Autowired
    dao daoInterface;
    @Override
    public boolean saveRm(RawMaterial rawMaterial) {
       if (daoInterface.rawMaterialIdExsist(rawMaterial.getRawMaterialId())) {
        return false;
       }
       rawMaterialRepository.save(rawMaterial);
       return true;
    }
    @Override
    public List<RawMaterial> getRm() {
        return rawMaterialRepository.findAll();
    }
    @Override
    public RawMaterial getRmById(Integer rawMaterialId) throws rawMaterialNotFoundException {
        Optional<RawMaterial> rawMaterial = rawMaterialRepository.findById(rawMaterialId);
        if (!rawMaterial.isPresent()) {
            throw new rawMaterialNotFoundException("Raw Material not available");
        }
        return rawMaterial.get();
    }
    @Override
    public RawMaterial updateRm(Integer rawMaterialId, RawMaterial rawMaterial) {
        System.out.println("Raw Material Updated");
        return rawMaterialRepository.save(rawMaterial);
    }
    
}
