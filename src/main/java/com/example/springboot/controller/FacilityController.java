package com.example.springboot.controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/product")
public class FacilityController{
    @Autowired
    FacilityService fs;

    @PostMapping("/facility")
    public ResponseEntity<String> createproduct(@RequestBody Product p)
    {
        return new ResponseEntity<String>(ps.saveProduct(p),HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Product>>readAllProduct()
    {
        return new ResponseEntity<List<Product>>(ps.getAllProduct(),HttpStatus.OK);
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<Product> readProductById(@PathVariable long id)
    {
        return new ResponseEntity<Product>(ps.getProductById(id),HttpStatus.OK);
    }

    @DeleteMapping("/deleteId")
    public ResponseEntity<String> deleteProductById(@RequestBody long id)
    {
        return new ResponseEntity<String>(ps.deleteProductById(id),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProductById(@RequestBody Product prod) throws NoProductFoundException
    {
        return new ResponseEntity<Product>(ps.updateProduct(prod.getProdId(),prod),HttpStatus.OK);
    }
    
}
