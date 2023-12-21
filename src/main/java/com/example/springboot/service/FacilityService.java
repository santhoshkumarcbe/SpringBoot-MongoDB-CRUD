package com.example.springboot.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.springboot.entity.Facility;

public interface FacilityService {

    String saveFacility(Facility f);
    List<Facility> getAllFacility();
    Facility getFacilityById(Long facilityId);
    String deleteFacilityById(Long facilityId);
    Facility updateFacility(Long facilityId,Facility f);
    String uploadImage(long Id, MultipartFile file);
    byte[] getImage(long Id);
}
