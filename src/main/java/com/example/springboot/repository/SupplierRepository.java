package com.example.springboot.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.springboot.entity.Supplier;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier,ObjectId>{

    boolean existsBySupplierName(String name);

    boolean existsBySupplierId(int supplierId);

    Supplier findBySupplierId(int supplierId);

    
    
}
