package com.example.springboot.service;


import java.io.File;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.springboot.entity.Facility;
import com.example.springboot.repository.FacilityRepository;

// import org.springframework.beans.factory.annotation.Autowired;

// import com.example.springboot.dao.dao;
// import com.example.springboot.repository.FacilityRepository;

@Service
public class FacilityServiceImpl implements FacilityService {
  private String imageFolder="C:\\Users\\ARULMOZHI K\\OneDrive\\Documents\\Intern\\n" + //
          "ew\\SpringBoot\\SpringBoot-MongoDB-CRUD\\src\\main\\resources\\uploads\\";
    @Autowired
    FacilityRepository repo;

    @Override
     public String saveFacility(Facility f)
     {  repo.save(f);
        return "Facility id:"+f.getFacilityId()+" is saved";
     }

     @Override
    public List<Facility> getAllFacility()
    {
        return repo.findAll();
    }

    @Override
    public Facility getFacilityById(Long Id)
    {
     return repo.findById(Id).get();
    }

    @Override
    public String deleteFacilityById(Long Id)
    {  boolean flag=false;
        if(repo.existsById(Id))
        {
            repo.deleteById(Id);
            flag=true;
        }
      return "Facility with Id: "+Id+" deletection status: "+flag;
    }

    @Override
    public Facility updateFacility(Long Id,Facility f)
    {   
        Optional<Facility> f1=repo.findById(Id);
        Facility f2=null;
        if(f1.isPresent()){
         f2=f1.get();
         f2.setFacilityName(f.getFacilityName());
         f2.setFacilityId(f.getFacilityId());
         f2.setSupplierId(f.getSupplierId());
         repo.save(f2);
        }
        return f2;
    }

    @Override 
    public String uploadImage(long Id, MultipartFile file)
    {  try{
        String imagePath=imageFolder+file.getOriginalFilename();
        Facility f=repo.findById(Id).get();
        f.setFacilityImage(imagePath);
        file.transferTo(new File(imagePath));
        repo.save(f);
        return "image uploaded at "+imagePath;
    }
    catch(Exception e)
    {
        e.printStackTrace();
        return "image not uploaded";
    }
    }

    @Override
    public byte[] getImage(long Id)
    {
        String path=repo.findById(Id).get().getFacilityImage();
        try{
            if(path==null)
            return null;
            byte[] image=Files.readAllBytes(new File(path).toPath());
            return image;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
