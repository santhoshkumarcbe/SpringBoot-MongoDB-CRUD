package com.example.springboot.service;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboot.dao.dao;
import com.example.springboot.entity.Supplier;
import com.example.springboot.errorHandling.supplierNotFoundError;
import com.example.springboot.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    dao daoInterface;
    @Override
    public boolean saveSupplier(Supplier supplier) throws supplierNotFoundError {
        
        int sId=supplier.getSupplierId();
        Supplier exsitingSupplier = supplierRepository.findBySupplierId(sId);
        boolean isESnull=false;
        if (exsitingSupplier==null) {
            isESnull=true;
        }
        if (isESnull) {
            supplierRepository.save(supplier);
            return true;
        }
        else{
        
        boolean isSId=supplierRepository.existsBySupplierId(supplier.getSupplierId());
        boolean sname=supplierRepository.existsBySupplierName(supplier.getSupplierName());
        // boolean slocation=supplierRepository.existsByLocation(supplier.getLocation());

        boolean slocation=false;
        
        if (exsitingSupplier.getLocation().containsAll(supplier.getLocation())) {
            slocation = true;
        }
    

        boolean smaterialType=false;
        if(exsitingSupplier.getMaterialType().containsAll(supplier.getMaterialType())){
            smaterialType=true;
            
        }
        ArrayList<Integer> newTier = exsitingSupplier.getTier();
        ArrayList<String> newMaterialType = exsitingSupplier.getMaterialType();
        ArrayList<String> newLocation = exsitingSupplier.getLocation();

        if ( isSId && sname) {
             if (slocation && sname) {
                if (smaterialType) {
                    // if (stier) {
                    //     return false;
                    // }
                //     else{
                //         newTier.addAll(supplier.getTier());
                //         supplier.setTier(newTier);
                //         supplier.setId(ExsitingSupplier.getId());
                //         supplierRepository.save(supplier);
                //         return true;
                //     }
                // }
                return false;
                }
                else{
                    
                    newLocation.addAll(supplier.getLocation());
                    supplier.setLocation(newLocation);
        
                    newMaterialType.addAll(supplier.getMaterialType());
                    supplier.setMaterialType(newMaterialType);

                    newTier.addAll(supplier.getTier());
                    supplier.setTier(newTier);

                    supplier.setId(exsitingSupplier.getId());
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

                supplier.setId(exsitingSupplier.getId());
                supplierRepository.save(supplier);
                return true;
            }
        }
        else{
            return false;
        }
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

    

