package com.example.springboot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.springboot.entity.Supplier;
import com.example.springboot.errorHandling.supplierNotFoundError;
import com.example.springboot.service.SupplierService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/add")

    public ResponseEntity<Supplier> saveSupplier(@RequestBody Supplier supplier) {
        try {
            if (supplierService.saveSupplier(supplier)) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.FOUND);
        } 
        catch (supplierNotFoundError e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    
    @GetMapping("/getall")
    public ResponseEntity<List<Supplier>> getSuppliers() {
        try {
            return new ResponseEntity<List<Supplier>> (supplierService.getSuppliers(),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Supplier>> ( HttpStatus.BAD_REQUEST);
        }   
    }

    @GetMapping("/get/{supplierId}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable("supplierId") int supplierId){
        try{
            return new ResponseEntity<>(supplierService.getSupplierById(supplierId),HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
             
    }

    @DeleteMapping("/delete/{supplierId}")
    public ResponseEntity<String> deleteSupplierById(@PathVariable("supplierId") ObjectId objectId){
        try{
            supplierService.deleteSupplierById(objectId);
            return new ResponseEntity<>("Supplier deleted successfully !!!",HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
        
    }

    @PutMapping("updateById/{Supplierid}")
    public ResponseEntity<Supplier> updatSupplier(@PathVariable("Supplierid") Integer supplierid, @RequestBody Supplier supplier) {
        try{
            return new ResponseEntity<>(supplierService.updatSupplier(supplierid,supplier),HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        
    }

    @PutMapping("updateByName/updateSupplierName")
    public void updateSupplierName(@RequestParam String oldName, @RequestParam String newName) {
        supplierService.updateSupplierName(oldName,newName);
    }


    @Controller public class UploadController {
    public final Path UPLOAD_DIRECTORY = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "uploads");

    @GetMapping("/{supplierId}/uploadimage")
    public String displayUploadForm(@PathVariable int supplierId, Model model) {
        model.addAttribute("supplierId", supplierId);
        return "index";
    }

    @PostMapping("/suppliers/{supplierId}/upload")
public String uploadImage(Model model, @RequestParam("image") MultipartFile file, @RequestParam int supplierId  ) throws IOException, supplierNotFoundError {
    if (!file.isEmpty() && file.getContentType().startsWith("image/")) {
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY.toString(), file.getOriginalFilename());

        if (!Files.exists(UPLOAD_DIRECTORY)) {
            Files.createDirectories(UPLOAD_DIRECTORY);
    }

    Files.write(fileNameAndPath, file.getBytes());
    model.addAttribute("msg", "Uploaded images: " + file.getOriginalFilename());

    String imagePath=fileNameAndPath.toString();
    supplierService.updateSupplierImagePath(imagePath,supplierId);
    }
    else{
        model.addAttribute("msg", "Invalid file. Please upload an image.");
    }
    
    return "index";
}
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
    
}
