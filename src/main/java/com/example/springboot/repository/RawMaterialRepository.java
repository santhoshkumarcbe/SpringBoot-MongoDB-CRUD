package com.example.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.RawMaterial;

@Repository
public interface RawMaterialRepository extends MongoRepository<RawMaterial,Integer>{

    
} 