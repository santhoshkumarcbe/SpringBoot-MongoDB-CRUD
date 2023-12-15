package com.example.springboot.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.entity.Supplier;
import com.example.springboot.errorHandling.supplierNotFoundError;
import com.example.springboot.repository.SupplierRepository;
import com.example.springboot.service.SupplierService;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    @Autowired
    private SupplierRepository supplierRepository;
    @PostMapping("/suppliers")
    public String saveSupplier(@RequestBody Supplier supplier) throws supplierNotFoundError {
         if(supplierService.saveSupplier(supplier)){
            return "Supplier saved successfully";
         }
         return "Supplier already exist";
        
    }

    @GetMapping("/suppliers")
    public List<Supplier> getSuppliers() {
        return supplierService.getSuppliers();
    }

    // @GetMapping("/suppliers/{supplierId}")
    // public Supplier getSupplierById(@PathVariable("supplierId") int supplierId) throws supplierNotFoundError {
    //         return supplierService.getSupplierById(supplierId); 
    // }

    @GetMapping("/suppliers/{supplierId}")
    public Supplier getSupplierById(@PathVariable("supplierId") int supplierId) throws supplierNotFoundError {
            return supplierRepository.findBySupplierId(supplierId); 
    }

    @DeleteMapping("/suppliers/{supplierId}")
    public String deleteSupplierById(@PathVariable("supplierId") ObjectId objectId){
        supplierService.deleteSupplierById(objectId);
        return "Supplier deleted successfully !!!";
    }

    @PutMapping("suppliers/{Supplierid}")
    public Supplier updatSupplier(@PathVariable("Supplierid") Integer supplierid, @RequestBody Supplier supplier) {
        return supplierService.updatSupplier(supplierid,supplier);
    }

    @PutMapping("suppliers/updateSupplierName")
    public void updateSupplierName(@RequestParam String oldName, @RequestParam String newName) {
        //TODO: process PUT request
        supplierService.updateSupplierName(oldName,newName);
    }
    
    
    
}
