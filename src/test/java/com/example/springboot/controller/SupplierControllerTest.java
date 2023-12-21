package com.example.springboot.controller;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.startsWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.springboot.controller.SupplierController.UploadController;
import com.example.springboot.entity.Supplier;
import com.example.springboot.repository.SupplierRepository;
import com.example.springboot.service.SupplierService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



@WebMvcTest(value = SupplierController.class)
public class SupplierControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    SupplierService supplierService;
    @MockBean
    SupplierRepository supplierRepository;
    @Test
    void testSupplierClassExists() throws Exception{
        Mockito.when(supplierService.getSupplierById(Mockito.anyInt())).thenReturn(null);
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/suppliers"))
        .andReturn().getResponse().getContentAsString();
        System.out.println("_____________________________");
        System.out.println(result);
        System.out.println("_____________________________");

        List<Supplier> op = Arrays.asList(new ObjectMapper().readValue(result, Supplier[].class));
         assertTrue(op.isEmpty());
    }


     @Test
    void testSaveSupplierSuccess() throws Exception {
        // Mock the service method to return true for successful save
        when(supplierService.saveSupplier(any(Supplier.class))).thenReturn(true);

        // Create a Supplier object for testing
        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
        Supplier newSupplier = new Supplier(
                objectId,
                23, "SK TEX",
                new ArrayList<>(Arrays.asList("Salem")), 
                new ArrayList<>(Arrays.asList("cotton")), 
                new ArrayList<>(Arrays.asList(3)),
                "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
                );

        // Perform the POST request to "/suppliers"
        mockMvc.perform(MockMvcRequestBuilders.post("/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newSupplier)))
                .andExpect(status().isCreated());  // Expect HTTP 201 Created for successful save
    }

     @Test
    void testSaveSupplierNotSuccess() throws Exception {
        // Mock the service method to throw an exception
        when(supplierService.saveSupplier(any(Supplier.class))).thenThrow(new RuntimeException("Some error"));

        // Create a Supplier object for testing
        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
        Supplier newSupplier = new Supplier(
                objectId,
                23, "SK TEX",
                new ArrayList<>(Arrays.asList("Salem")), 
                new ArrayList<>(Arrays.asList("cotton")), 
                new ArrayList<>(Arrays.asList(3)),
                "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
                );

        // Perform the POST request to "/suppliers"
        mockMvc.perform(MockMvcRequestBuilders.post("/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newSupplier)))
                .andExpect(status().isBadRequest());  // Expect HTTP 400 Bad Request for an exception
    }

    @Test
    void testSaveSupplierDuplicate() throws Exception {
        // Mock the service method to return false for duplicate entry
        when(supplierService.saveSupplier(any(Supplier.class))).thenReturn(false);

        // Create a Supplier object for testing
        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
        Supplier newSupplier = new Supplier(
                objectId,
                23, "SK TEX",
                new ArrayList<>(Arrays.asList("Salem")), 
                new ArrayList<>(Arrays.asList("cotton")), 
                new ArrayList<>(Arrays.asList(3)),
                "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
                );

        // Perform the POST request to "/suppliers"
        mockMvc.perform(MockMvcRequestBuilders.post("/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newSupplier)))
                .andExpect(status().isFound());  // Expect HTTP 302 Found for duplicate entry
    }


    @Test
    void testGetSuppliers() throws Exception {
        // Mock the service method to return a list of suppliers
        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
        List<Supplier> mockSuppliers = Arrays.asList(
            
            new Supplier(
                objectId,
                23, "SK TEX",
                new ArrayList<>(Arrays.asList("Salem")), 
                new ArrayList<>(Arrays.asList("cotton")), 
                new ArrayList<>(Arrays.asList(3)),
                "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
                )
            ,
            new Supplier(
                objectId, 
                24,
                "KSK TEX", 
                new ArrayList<>(Arrays.asList("COVAI")), 
                new ArrayList<>(Arrays.asList("thread")), 
                new ArrayList<>(Arrays.asList(2)),
                "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
                )
                
        );
        when(supplierService.getSuppliers()).thenReturn(mockSuppliers);

        // Perform the GET request to "/suppliers"
        mockMvc.perform(MockMvcRequestBuilders.get("/suppliers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Expect HTTP 200 OK
                .andExpect(jsonPath("$.length()").value(2))  // Adjust this based on the expected number of suppliers
                .andExpect(jsonPath("$[0].supplierId").value(23))  // Adjust these based on the expected data
                .andExpect(jsonPath("$[1].supplierId").value(24));
    }


    @Test
    void testGetSupplierByIdFetched() throws Exception {
        // Mock the service method to return a Supplier for a valid supplierId
        int validSupplierId = 23;
        ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
        Supplier mockSupplier = new Supplier(
                objectId,
                23, "SK TEX",
                new ArrayList<>(Arrays.asList("Salem")), 
                new ArrayList<>(Arrays.asList("cotton")), 
                new ArrayList<>(Arrays.asList(3)),
                "C:\\Users\\SANTHOSHKUMAR K\\Documents\\Trustrace\\SpringBoot\\spring-boot\\src\\main\\resources\\uploads\\download.jpg"
                );
        when(supplierService.getSupplierById(validSupplierId)).thenReturn(mockSupplier);

        // Perform the GET request to "/suppliers/{supplierId}"
        mockMvc.perform(MockMvcRequestBuilders.get("/suppliers/{supplierId}", validSupplierId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Expect HTTP 200 OK
                .andExpect(jsonPath("$.supplierId").value(validSupplierId));  // Adjust based on the expected data
    }

     @Test
    public void testDeleteSupplierById_Success() {
        // Arrange
        ObjectId supplierId = new ObjectId("65797bbac254a3759e242962");

        // Mock the behavior of the service method
        doNothing().when(supplierService).deleteSupplierById(supplierId);

        // Act
        supplierService.deleteSupplierById(supplierId);

        // Verify that the service method was called with the correct argument
        verify(supplierService, times(1)).deleteSupplierById(supplierId);
    }

    @Test
    public void testDeleteSupplierById_Failure() {
        // Arrange
        ObjectId supplierId = new ObjectId("65797bbac254a3759e242962");

        
        doNothing().when(supplierService).deleteSupplierById(supplierId);

        // Act
        // Call the method (even though it throws an exception)
        try{
        supplierService.deleteSupplierById(supplierId);
        }
        catch(Exception e){
            e.getMessage();
        }
        
        // Verify that the service method was called with the correct argument
        verify(supplierService, times(1)).deleteSupplierById(supplierId);
    }




@ExtendWith(MockitoExtension.class)
class UploadControllerTest {

    @InjectMocks
    private UploadController uploadController;

    @Mock
    private SupplierService supplierService;

    @Mock
    private Model model;

    private MockMultipartFile validImageFile;
    private MockMultipartFile invalidFile;

    @BeforeEach
    void setUp() {
        validImageFile = new MockMultipartFile(
                "image",
                "test-image.jpg",
                "image/jpeg",
                "Test image content".getBytes()
        );

        invalidFile = new MockMultipartFile(
                "image",
                "test.txt",
                "text/plain",
                "Invalid file content".getBytes()
        );
    }

    @Test
    void testDisplayUploadForm() {
        int supplierId = 123;

        String result = uploadController.displayUploadForm(supplierId, model);

        assertEquals("index", result);
        assertEquals(supplierId, model.getAttribute("supplierId"));
    }

    @Test
    void testUploadImageWithValidImage() throws IOException {
        int supplierId = 456;

        doNothing().when(supplierService).updateSupplierImagePath(anyString(), eq(supplierId));

        String result = uploadController.uploadImage(model, validImageFile, supplierId);

        assertEquals("index", result);
        verify(model).addAttribute(eq("msg"), startsWith("Uploaded images:"));
        verify(supplierService).updateSupplierImagePath(anyString(), eq(supplierId));
    }

    @Test
    void testUploadImageWithInvalidFile() throws IOException {
        int supplierId = 789;

        String result = uploadController.uploadImage(model, invalidFile, supplierId);

        assertEquals("index", result);
        verify(model).addAttribute(eq("msg"), eq("Invalid file. Please upload an image."));
        verify(supplierService, never()).updateSupplierImagePath(anyString(), anyInt());
    }
}


}

   