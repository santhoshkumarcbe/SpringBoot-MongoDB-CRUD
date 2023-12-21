package com.example.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.springboot.entity.Supplier;
import com.example.springboot.repository.SupplierRepository;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {
    
    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Test
    public void testSupplierCreatedSuccessfully() throws Exception{

        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
        Supplier newSupplier = new Supplier(objectId,23, "SK TEX", new ArrayList<>(List.of("Coimbatore")), new ArrayList<>(List.of("cotton")), new ArrayList<>(List.of(3)),"C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg");
        Supplier ExistingSupplier = new Supplier(objectId,24, "KSK TEX", new ArrayList<>(List.of("Salem")), new ArrayList<>(List.of("cotton")), new ArrayList<>(List.of(3)),"C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg");
        newSupplier.setLocation(new ArrayList<>()); // Set to empty to trigger the location update logic
        when(supplierRepository.existsBySupplierId(newSupplier.getSupplierId())).thenReturn(false);
        when(supplierRepository.existsBySupplierName(newSupplier.getSupplierName())).thenReturn(false);
        when(supplierRepository.findBySupplierId(newSupplier.getSupplierId())).thenReturn(ExistingSupplier);

         // Act
        boolean result = supplierService.saveSupplier(newSupplier);

        // Assert
        assertTrue(result);

    }
    @Test
    public void testSupplierCannotCreatedSuccessfully() throws Exception {
        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
        Supplier newSupplier = new Supplier(objectId, 23,
      "SK TEX",
      new ArrayList<>(List.of("Salem")),
      new ArrayList<>(List.of("cotton")),
      new ArrayList<>(List.of(3)),
      "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
    );
        Supplier ExistingSupplier = new Supplier(objectId, 23,
      "SK TEX",
      new ArrayList<>(List.of("Salem")),
      new ArrayList<>(List.of("cotton")),
      new ArrayList<>(List.of(3)),
      "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
    );
        newSupplier.setLocation(new ArrayList<>()); // Set to empty to trigger the location update logic
        when(supplierRepository.existsBySupplierId(newSupplier.getSupplierId())).thenReturn(true);
        when(supplierRepository.existsBySupplierName(newSupplier.getSupplierName())).thenReturn(true);
        when(supplierRepository.findBySupplierId(newSupplier.getSupplierId())).thenReturn(ExistingSupplier);

         // Act
        boolean result = supplierService.saveSupplier(newSupplier);

        // Assert
        assertFalse(result);

        // verify(supplierRepository, times(5)).save(newSupplier);
    }

    @Test
    public void testSupllierGetsData() throws Exception {
        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
        Supplier newSupplier = new Supplier(objectId, 23,
      "SK TEX",
      new ArrayList<>(List.of("Salem")),
      new ArrayList<>(List.of("cotton")),
      new ArrayList<>(List.of(3)),
      "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
    );
        Mockito.when(supplierRepository.findAll()).thenReturn(List.of(newSupplier));
        List<Supplier> result = supplierService.getSuppliers();
        assertEquals(List.of(newSupplier), result);
    }

    @Test
    public void testSupllierNotGetsData() throws Exception {
        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
        Supplier newSupplier = new Supplier(objectId, 23,
      "SK TEX",
      new ArrayList<>(List.of("Salem")),
      new ArrayList<>(List.of("cotton")),
      new ArrayList<>(List.of(3)),
      "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
    );
        Mockito.when(supplierRepository.findAll()).thenThrow(RuntimeException.class);
        List<Supplier> result = supplierService.getSuppliers();
        assertNotEquals(List.of(newSupplier), result);
    }

    @Test
    public void testSupplierCanGetById() throws Exception {
        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
        Supplier newSupplier = new Supplier(objectId, 23,
      "SK TEX",
      new ArrayList<>(List.of("Salem")),
      new ArrayList<>(List.of("cotton")),
      new ArrayList<>(List.of(3)),
      "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
    );
        Mockito.when(supplierRepository.findBySupplierId(23)).thenReturn(newSupplier);
        Supplier result = supplierService.getSupplierById(23);
        assertEquals(newSupplier, result);
    }

    @Test
    public void testSupplierCanotGetById() throws Exception {
        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
        Supplier newSupplier = new Supplier(objectId, 23,
      "SK TEX",
      new ArrayList<>(List.of("Salem")),
      new ArrayList<>(List.of("cotton")),
      new ArrayList<>(List.of(3)),
      "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
    );
        Mockito.when(supplierRepository.findBySupplierId(76)).thenThrow(RuntimeException.class);
        Supplier result = supplierService.getSupplierById(23);
        assertNotEquals(newSupplier, result);
    }

    @Test
    public void testSupplierCanUpdate() throws Exception {
        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
        Supplier newSupplier = new Supplier(objectId, 23,
      "SK TEX",
      new ArrayList<>(List.of("Salem")),
      new ArrayList<>(List.of("cotton")),
      new ArrayList<>(List.of(3)),
      "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
    );
        Mockito.when(supplierRepository.save(newSupplier)).thenReturn(newSupplier);
        Supplier result = supplierService.updatSupplier(23, newSupplier);
        assertEquals(newSupplier, result);
    }

    @Test
    public void testSupplierCannotUpdated() throws Exception {
        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
        Supplier newSupplier = new Supplier(objectId, 23,
      "SK TEX",
      new ArrayList<>(List.of("Salem")),
      new ArrayList<>(List.of("cotton")),
      new ArrayList<>(List.of(3)),
      "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
    );
        Mockito.when(supplierRepository.save(newSupplier)).thenThrow(RuntimeException.class);
        Supplier result = supplierService.updatSupplier(21,newSupplier);
        assertNotEquals(newSupplier, result);
    }

    @Test
    public void testSupplierCannotDeleted() throws Exception {
        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
    Supplier newSupplier = new Supplier(objectId, 23,
      "SK TEX",
      new ArrayList<>(List.of("Salem")),
      new ArrayList<>(List.of("cotton")),
      new ArrayList<>(List.of(3)),
      "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
    );
    supplierRepository.deleteById(newSupplier.getId());
    verify(supplierRepository, times(1)).deleteById(objectId);
    }

    @Test
    public void testSupplierCanDeleted() throws Exception {
        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
    // Supplier newSupplier = new Supplier(new ObjectId("65797bbac254a3759e242963"), 
    // 24, 
    // "SK TEX", 
    // new ArrayList<>(List.of("Salem")), 
    // new ArrayList<>(List.of("cotton")), 
    // new ArrayList<>(List.of(3)),
    // "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
    // );
    supplierRepository.deleteById(objectId);
    // supplierService.deleteSupplierById(objectId);
    verify(supplierRepository, times(1)).deleteById(objectId);
    }




}
