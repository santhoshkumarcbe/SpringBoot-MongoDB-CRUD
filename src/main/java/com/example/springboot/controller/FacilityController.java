package com.example.springboot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.springboot.entity.Facility;
import com.example.springboot.service.FacilityService;

import java.util.List;

//import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/facility")
public class FacilityController{
    @Autowired
    FacilityService fs;

    @PostMapping("/add")
    public ResponseEntity<String> createproduct(@RequestBody Facility f)
    {
        return new ResponseEntity<String>(fs.saveFacility(f),HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Facility>>readAllProduct()
    {
        return new ResponseEntity<List<Facility>>(fs.getAllFacility(),HttpStatus.OK);
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<Facility> readProductById(@PathVariable long id)
    {
        return new ResponseEntity<Facility>(fs.getFacilityById(id),HttpStatus.OK);
    }

    @DeleteMapping("/deleteId")
    public ResponseEntity<String> deleteProductById(@RequestBody long id)
    {
        return new ResponseEntity<String>(fs.deleteFacilityById(id),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Facility> updateProductById(@RequestBody Facility f)
    {
        return new ResponseEntity<Facility>(fs.updateFacility(f.getFacilityId(),f),HttpStatus.OK);
    }
    
    @PutMapping("/uploadImage/{Id}")
    public ResponseEntity<String> updateImage(@PathVariable long Id,@RequestBody MultipartFile file)
    {
        return new ResponseEntity<String>(fs.uploadImage(Id,file),HttpStatus.OK);
    }

    @GetMapping("getImage/{Id}")
    public ResponseEntity<?> getImage(@PathVariable long Id)
    {
        return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.valueOf("image/jpeg"))
        .body(fs.getImage(Id));
    }
 }
