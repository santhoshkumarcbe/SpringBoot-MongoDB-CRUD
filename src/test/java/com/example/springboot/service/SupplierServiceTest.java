package com.example.springboot.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
    public void SupplierSaveSuccessfully() throws Exception{
        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
        Supplier newSupplier = new Supplier(objectId,23, "SK TEX", new ArrayList<>(List.of("Salem")), new ArrayList<>(List.of("cotton")), new ArrayList<>(List.of(3)));
        Supplier ExistingSupplier = new Supplier(objectId,23, "SK TEX", new ArrayList<>(List.of("Salem")), new ArrayList<>(List.of("cotton")), new ArrayList<>(List.of(3)));
        newSupplier.setLocation(new ArrayList<>()); // Set to empty to trigger the location update logic
        when(supplierRepository.existsBySupplierId(newSupplier.getSupplierId())).thenReturn(true);
        when(supplierRepository.existsBySupplierName(newSupplier.getSupplierName())).thenReturn(true);
        when(supplierRepository.findBySupplierId(newSupplier.getSupplierId())).thenReturn(ExistingSupplier);
        when(supplierRepository.findBySupplierId(newSupplier.getSupplierId())).thenReturn(ExistingSupplier);

         // Act
        boolean result = supplierService.saveSupplier(newSupplier);

        // Assert
        assertFalse(result);

        verify(supplierRepository, times(1)).save(newSupplier);
    }
}
