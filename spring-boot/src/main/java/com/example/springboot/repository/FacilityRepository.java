package com.example.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.springboot.entity.Facility;

public interface FacilityRepository extends MongoRepository<Facility,Integer>{

    
} 