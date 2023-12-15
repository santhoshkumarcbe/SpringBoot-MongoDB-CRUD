package com.example.springboot.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.same;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.springboot.entity.Supplier;
import com.example.springboot.repository.SupplierRepository;
import com.example.springboot.service.SupplierService;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(value = SupplierController.class)
public class SupplierControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    // @SpyBean
    SupplierService service;
    @MockBean
    SupplierRepository supplierRepository;

    @Test
    void testSupplierClassExists() throws Exception{
        Mockito.when(service.getSupplierById(Mockito.anyInt())).thenReturn(null);
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/suppliers"))
        .andReturn().getResponse().getContentAsString();
        System.out.println("_____________________________");
        System.out.println(result);
        System.out.println("_____________________________");

        List<Supplier> op = Arrays.asList(new ObjectMapper().readValue(result, Supplier[].class));
         assertTrue(op.isEmpty());
    }

}
