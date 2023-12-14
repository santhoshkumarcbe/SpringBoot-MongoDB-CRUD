package com.example.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entity.RawMaterial;
import com.example.springboot.errorHandling.rawMaterialNotFoundException;
import com.example.springboot.service.RawMaterialService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class RawMaterialController {
    @Autowired
    private RawMaterialService rawMaterialService;
    @PostMapping("/rawmaterial")
    public String saveRm(@RequestBody RawMaterial rawMaterial) {
        if (rawMaterialService.saveRm(rawMaterial)) {
            return "RawMaterial saved successfully";
         }
         return "RawMaterial already exist";
    }

    @GetMapping("/rawmaterial")
    public List<RawMaterial> getRm() {
        return rawMaterialService.getRm();
    }

    @GetMapping("/rawmaterial/{id}")
    public RawMaterial getRmById (@PathVariable("id")Integer rawMaterialId) throws rawMaterialNotFoundException {
        return rawMaterialService.getRmById(rawMaterialId);
    }

    @PutMapping("rawmaterial/{id}")
    public RawMaterial updateRm(@PathVariable("rawMaterialId") Integer rawMaterialId, @RequestBody RawMaterial rawMaterial) {
        return rawMaterialService.updateRm(rawMaterialId,rawMaterial);
    }
    
    
    
}
