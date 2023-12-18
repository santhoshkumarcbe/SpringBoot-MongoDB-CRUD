package com.example.springboot.service;

import java.util.ArrayList;

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
    private SupplierServiceImpl service;

    @Test
    public void SupplierGetsSuccessfully() throws Exception{
        Supplier newSupplier=new Supplier(null, 0, null, null, null, null);
        
    }
}
