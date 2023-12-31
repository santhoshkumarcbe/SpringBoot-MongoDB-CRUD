package com.example.springboot.service;

import java.util.List;
import org.bson.types.ObjectId;
import com.example.springboot.entity.Supplier;
import com.example.springboot.errorHandling.supplierNotFoundError;

public interface SupplierService {

    public boolean saveSupplier(Supplier supplier) throws supplierNotFoundError;

    public List<Supplier> getSuppliers() throws supplierNotFoundError;

    public Supplier getSupplierById(Integer supplierId) throws supplierNotFoundError;

    public void deleteSupplierById(ObjectId objectId);

    public Supplier updatSupplier(Integer supplierid, Supplier supplier);

    public void updateSupplierName(String oldName, String newName);

    public void updateSupplierImagePath(String imagePath, int supplierId) throws supplierNotFoundError;

    // public Page<Supplier> findAll(PageRequest pageable);

    
} 
