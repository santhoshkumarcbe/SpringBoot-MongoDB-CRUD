package com.example.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.dao.dao;
import com.example.springboot.entity.Supplier;
import com.example.springboot.errorHandling.supplierNotFoundError;
import com.example.springboot.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService{

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    dao daoInterface;
    @Override
    public boolean saveSupplier(Supplier supplier) throws supplierNotFoundError {
        // if(daoInterface.idExsist(supplier.getSupplierId())){
        //     return false;
        // }
        boolean sId=supplierRepository.existsBySupplierId(supplier.getSupplierId());
        boolean sname=supplierRepository.existsBySupplierName(supplier.getSupplierName());
        boolean slocation=supplierRepository.existsByLocation(supplier.getLocation());
        boolean smaterialType=supplierRepository.existsByMaterialType(supplier.getMaterialType());
        boolean stier=supplierRepository.existsByTier(supplier.getTier());

        int sId1=supplier.getSupplierId();

        if ( sId && sname){
            if (slocation &&sname) {
                return false;
            }
            else{
                Supplier ExsitingSupplier=supplierRepository.findBySupplierId(sId1);
                ArrayList<String> newLocation = ExsitingSupplier.getLocation();
                newLocation.addAll(supplier.getLocation());
                supplier.setLocation(newLocation);
                supplier.setId(ExsitingSupplier.getId());
                supplierRepository.save(supplier);
                return true;
            }
        }
        else{
        supplierRepository.save(supplier);
        return true;
        }
    }
    
    @Override
    public List<Supplier> getSuppliers() {
        return supplierRepository.findAll();
    }
    @Override
    public Supplier getSupplierById(Integer supplierId) throws supplierNotFoundError {
        Optional<Supplier> supplier= supplierRepository.findById(supplierId);
        if (!supplier.isPresent()) {
            throw new supplierNotFoundError("Supplier not available");
            
        }
        return supplier.get();
        
    }
    @Override
    public void deleteSupplierById(Integer supplierId) {
        supplierRepository.deleteById(supplierId);
    }
    @Override
    public Supplier updatSupplier(Integer supplierid, Supplier supplier) {
        System.out.println("Supplier updated");
        return supplierRepository.save(supplier);
            
        }
    
    
    @Override
    public void updateSupplierName(String oldName, String newName) {
        // TODO Auto-generated method stub
        daoInterface.updateSupplierName(oldName, newName);
        
    }
    }

    

