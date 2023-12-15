package com.example.springboot.service;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

        boolean isExsitingSupplierNull=false;
        if (ExsitingSupplier==null) {
            isExsitingSupplierNull=true;
        }

        
        boolean isSupplierId=supplierRepository.existsBySupplierId(supplier.getSupplierId());
        boolean sname=supplierRepository.existsBySupplierName(supplier.getSupplierName());

        boolean slocation=false;
        
        if (!isExsitingSupplierNull && ExsitingSupplier.getLocation()!=null && ExsitingSupplier.getLocation().containsAll(supplier.getLocation())) {
            slocation=true;
        }
    

        boolean smaterialType=false;
        if(!isExsitingSupplierNull && ExsitingSupplier.getMaterialType()!=null && ExsitingSupplier.getMaterialType().containsAll(supplier.getMaterialType())){
            smaterialType=true;
        }

        

        if ( isSupplierId) {
            if (sname) {
             if (slocation) {
                if (smaterialType) {
                    return false;
                }
                else{
                    ArrayList<Integer> newTier = ExsitingSupplier.getTier();
                    ArrayList<String> newMaterialType = ExsitingSupplier.getMaterialType();
                    ArrayList<String> newLocation = ExsitingSupplier.getLocation();

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
                ArrayList<Integer> newTier = ExsitingSupplier.getTier();
                ArrayList<String> newMaterialType = ExsitingSupplier.getMaterialType();
                ArrayList<String> newLocation = ExsitingSupplier.getLocation();

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
            return false;
        }
       
        }
         else{
            return false;
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
        daoInterface.updateSupplierName(oldName, newName);
        
    }

    // @Override
    // public Object getById(String anyString) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getById'");
    // }

    // @Override
    // public Page<Supplier> getAllSuppliers(int page, int size, String sortBy) {
    //     PageRequest pageable = PageRequest.of(page, size, Sort.by(sortBy));
    //     return supplierRepository.findAll(pageable);
    // }
    
    }

    

