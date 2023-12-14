package com.example.springboot.service;

import java.util.List;

import com.example.springboot.entity.RawMaterial;
import com.example.springboot.errorHandling.rawMaterialNotFoundException;

public interface RawMaterialService {

    public boolean saveRm(RawMaterial rawMaterial);

    public List<RawMaterial> getRm();

    public RawMaterial getRmById(Integer rawMaterialId) throws rawMaterialNotFoundException;

    public RawMaterial updateRm(Integer rawMaterialId, RawMaterial rawMaterial);

    
} 