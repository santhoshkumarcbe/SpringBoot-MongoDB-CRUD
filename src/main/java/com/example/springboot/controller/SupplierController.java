package com.example.springboot.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.entity.Supplier;
import com.example.springboot.errorHandling.supplierNotFoundError;
import com.example.springboot.repository.SupplierRepository;
import com.example.springboot.service.SupplierService;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/suppliers")
    public ResponseEntity<Void> saveSupplier(@RequestBody Supplier supplier) {
        try {
            if (supplierService.saveSupplier(supplier)) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.FOUND);
        } catch (supplierNotFoundError e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    

    @GetMapping("/suppliers")
    public List<Supplier> getSuppliers() {

        return supplierService.getSuppliers();
    }

    // @GetMapping("/suppliers/sort/")
    // public List<Supplier> getSortSuppliers(
    //     @RequestParam(defaultValue = "0") int page,
    //         @RequestParam(defaultValue = "10") int size,
    //         @RequestParam(defaultValue = "id") String sortBy
    // ) {
    //     PageRequest pageable=PageRequest.of(page, size,Sort.by(sortBy));
    //     return supplierService.findAll(pageable);
    // }


    @GetMapping("/suppliers/{supplierId}")
    public Supplier getSupplierById(@PathVariable("supplierId") int supplierId) throws supplierNotFoundError {
            return supplierService.getSupplierById(supplierId); 
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
