package com.example.springboot.service;

import java.util.List;

import com.example.springboot.entity.Supplier;
import com.example.springboot.errorHandling.supplierNotFoundError;

public interface SupplierService {

    public boolean saveSupplier(Supplier supplier) throws supplierNotFoundError;

    public List<Supplier> getSuppliers();

    public Supplier getSupplierById(Integer supplierId) throws supplierNotFoundError;

    public void deleteSupplierById(Integer supplierId);

    public Supplier updatSupplier(Integer supplierid, Supplier supplier);

    public void updateSupplierName(String oldName, String newName);

    
} 
