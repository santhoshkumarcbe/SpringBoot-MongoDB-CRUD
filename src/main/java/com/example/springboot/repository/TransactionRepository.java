package com.example.springboot.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.Transaction;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction,ObjectId>{

    Transaction findBySupplierId(int supplierId);

    boolean existsBySupplierId(int supplierId);

    boolean existsBySupplierName(String supplierName);
    
}
