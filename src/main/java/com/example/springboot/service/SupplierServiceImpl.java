package com.example.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
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
        int sId=supplier.getSupplierId();
        Supplier ExsitingSupplier=supplierRepository.findBySupplierId(sId);

        boolean isSId=false;
        if(supplier.getSupplierId()==ExsitingSupplier.getSupplierId()){
            isSId=true;
        }

        boolean sname=false;
        if(supplier.getSupplierName()==ExsitingSupplier.getSupplierName()){
            sname=true;
        }

        boolean slocation=false;
        if(supplier.getLocation()==ExsitingSupplier.getLocation()){
            slocation=true;
        }

        boolean smaterialType=false;
        if(supplier.getMaterialType()==ExsitingSupplier.getMaterialType()){
            smaterialType=true;
        }

        boolean stier=false;
        if(supplier.getTier()==ExsitingSupplier.getTier()){
            stier=true;
        }
        
        ArrayList<Integer> newTier = ExsitingSupplier.getTier();
        ArrayList<String> newMaterialType = ExsitingSupplier.getMaterialType();
        ArrayList<String> newLocation = ExsitingSupplier.getLocation();

        if ( isSId && sname){
            if (slocation) {
                if (smaterialType) {
                    if (stier) {
                        return false;
                    }
                    else{
                        newTier.addAll(supplier.getTier());
                        supplier.setTier(newTier);
                        supplier.setId(ExsitingSupplier.getId());
                        supplierRepository.save(supplier);
                        return true;
                    }
                }
                else{
        
                    newMaterialType.addAll(supplier.getMaterialType());
                    supplier.setMaterialType(newMaterialType);

                    newTier.addAll(supplier.getTier());
                    supplier.setTier(newTier);

                    supplier.setId(ExsitingSupplier.getId());
                    supplierRepository.save(supplier);
                    return true;
                }
            }
            else{
                newLocation.addAll(supplier.getLocation());
                supplier.setLocation(newLocation);

                newMaterialType.addAll(supplier.getMaterialType());
                supplier.setMaterialType(newMaterialType);

                newTier.addAll(supplier.getTier());
                supplier.setTier(newTier);

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
        Supplier supplier= supplierRepository.findBySupplierId(supplierId);
        if (supplier==null) {
            throw new supplierNotFoundError("Supplier not available");
            
        }
        return supplier;
        
    }
    @Override
    public void deleteSupplierById(ObjectId objectId) {
        supplierRepository.deleteById(objectId);
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

    

