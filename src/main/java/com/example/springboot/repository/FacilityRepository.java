package com.example.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.Facility;
@Repository
public interface FacilityRepository extends MongoRepository<Facility,Long>{

    
} 