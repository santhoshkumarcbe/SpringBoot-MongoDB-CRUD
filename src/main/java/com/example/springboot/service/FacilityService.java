package com.example.springboot.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.springboot.entity.Facility;
import com.example.springboot.errorHandling.facilityNotFoundError;

public interface FacilityService {

    Boolean saveFacility(Facility f);
    List<Facility> getAllFacility();
    Facility getFacilityById(Long facilityId) throws facilityNotFoundError, Exception;
    Boolean deleteFacilityById(Long facilityId);
    Facility updateFacility(Long facilityId,Facility f) throws Exception;
    Boolean uploadImage(long Id, MultipartFile file) throws Exception;
    byte[] getImage(long Id);
}
