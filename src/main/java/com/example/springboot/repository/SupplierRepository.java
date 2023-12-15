package com.example.springboot.repository;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.springboot.entity.Supplier;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier,ObjectId>{

    boolean existsBySupplierName(String name);

    boolean existsByLocation(ArrayList<String> arrayList);

    boolean existsByMaterialType(ArrayList<String> arrayList);

    boolean existsBySupplierId(int supplierId);

    boolean existsByTier(ArrayList<Integer> arrayList);

    Supplier findBySupplierId(int supplierId);

    
    
}
