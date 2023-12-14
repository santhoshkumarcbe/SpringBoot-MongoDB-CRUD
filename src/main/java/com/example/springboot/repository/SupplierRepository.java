package com.example.springboot.repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.springboot.entity.Supplier;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier,Integer>{

    boolean existsBySupplierName(String name);

    boolean existsByLocation(ArrayList<String> arrayList);

    boolean existsByMaterialType(String materialType);

    boolean existsBySupplierId(int supplierId);

    boolean existsByTier(int tier);

    Supplier findBySupplierId(int supplierId);
    
}
