package com.example.springboot.controller;
import com.example.springboot.errorHandling.*;
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
    public ResponseEntity<Facility> createproduct(@RequestBody Facility f)
    {    try{
        if(fs.saveFacility(f))
        return new ResponseEntity<>(HttpStatus.CREATED);
        else
        throw new facilityFoundError();
        }
        catch(facilityFoundError e){
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
   //  
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Facility>>readAllProduct()
    {
        return new ResponseEntity<List<Facility>>(fs.getAllFacility(),HttpStatus.OK);
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<Facility> readProductById(@PathVariable long id)
    {   try{
        Facility f=fs.getFacilityById(id);
        if(f!=null)
        return new ResponseEntity<Facility>(f,HttpStatus.OK);
        else
          return new ResponseEntity<Facility>(f,HttpStatus.NOT_FOUND);
         }
         catch(Exception e){
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
    }

    @DeleteMapping("/deleteId/{id}")
    public ResponseEntity<String> deleteFacilityById(@PathVariable long id)
    {  
         try{
         if(fs.deleteFacilityById(id))
        return new ResponseEntity<String>("Id: "+id+" Facility Deleted.",HttpStatus.OK);
        else
          return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
         }
         catch(Exception e){
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
    }

    @PutMapping("/update")
    public ResponseEntity<Facility> updateProductById(@RequestBody Facility f)
    {   try{ Facility updateFacility=fs.updateFacility(f.getFacilityId(),f);
             if(updateFacility!=null)
            return new ResponseEntity<Facility>(updateFacility,HttpStatus.OK);
            else
            return new ResponseEntity<Facility>(HttpStatus.NOT_FOUND);
           }
           catch(Exception e)
           {
           return new ResponseEntity<Facility>(HttpStatus.BAD_REQUEST);
           }
    }
    
    @PutMapping("/uploadImage/{Id}")
    public ResponseEntity<String> updateImage(@PathVariable long Id,@RequestBody MultipartFile file)
    {try{
        boolean imageStatus=fs.uploadImage(Id,file);
        if(imageStatus)
        return new ResponseEntity<String>("Image Uploaded.",HttpStatus.OK);
        else
         return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
    catch(Exception e)
    {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

    @GetMapping("getImage/{Id}")
    public ResponseEntity<?> getImage(@PathVariable long Id)
    {
        try{
        return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.valueOf("image/jpeg"))
        .body(fs.getImage(Id));
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 }
