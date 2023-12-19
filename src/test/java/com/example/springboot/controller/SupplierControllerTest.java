// package com.example.springboot.controller;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.fail;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.doThrow;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import org.springframework.http.MediaType;

// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// // import org.apache.tomcat.util.http.parser.MediaType;
// import org.bson.types.ObjectId;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import com.example.springboot.entity.Supplier;
// import com.example.springboot.errorHandling.supplierNotFoundError;
// import com.example.springboot.repository.SupplierRepository;
// import com.example.springboot.service.SupplierService;
// import com.fasterxml.jackson.databind.ObjectMapper;



// @WebMvcTest(value = SupplierController.class)
// public class SupplierControllerTest {
//     @Autowired
//     MockMvc mockMvc;
//     @MockBean
//     SupplierService supplierService;
//     @MockBean
//     SupplierRepository supplierRepository;
//     @Test
//     void testSupplierClassExists() throws Exception{
//         Mockito.when(supplierService.getSupplierById(Mockito.anyInt())).thenReturn(null);
//         String result = mockMvc.perform(MockMvcRequestBuilders.get("/suppliers"))
//         .andReturn().getResponse().getContentAsString();
//         System.out.println("_____________________________");
//         System.out.println(result);
//         System.out.println("_____________________________");

//         List<Supplier> op = Arrays.asList(new ObjectMapper().readValue(result, Supplier[].class));
//          assertTrue(op.isEmpty());
//     }


//      @Test
//     void testSaveSupplierSuccess() throws Exception {
//         // Mock the service method to return true for successful save
//         when(supplierService.saveSupplier(any(Supplier.class))).thenReturn(true);

//         // Create a Supplier object for testing
//         ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
//         Supplier newSupplier = new Supplier(objectId, 23, "SK TEX", new ArrayList<>(List.of("Salem")), new ArrayList<>(List.of("cotton")), new ArrayList<>(List.of(3)));

//         // Perform the POST request to "/suppliers"
//         mockMvc.perform(MockMvcRequestBuilders.post("/suppliers")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(new ObjectMapper().writeValueAsString(newSupplier)))
//                 .andExpect(status().isCreated());  // Expect HTTP 201 Created for successful save
//     }

//      @Test
//     void testSaveSupplierNotSuccess() throws Exception {
//         // Mock the service method to throw an exception
//         when(supplierService.saveSupplier(any(Supplier.class))).thenThrow(new RuntimeException("Some error"));

//         // Create a Supplier object for testing
//         ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
//         Supplier newSupplier = new Supplier(objectId, 23, "SK TEX", new ArrayList<>(List.of("Salem")), new ArrayList<>(List.of("cotton")), new ArrayList<>(List.of(3)));

//         // Perform the POST request to "/suppliers"
//         mockMvc.perform(MockMvcRequestBuilders.post("/suppliers")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(new ObjectMapper().writeValueAsString(newSupplier)))
//                 .andExpect(status().isBadRequest());  // Expect HTTP 400 Bad Request for an exception
//     }

//     @Test
//     void testSaveSupplierDuplicate() throws Exception {
//         // Mock the service method to return false for duplicate entry
//         when(supplierService.saveSupplier(any(Supplier.class))).thenReturn(false);

//         // Create a Supplier object for testing
//         ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
//         Supplier newSupplier = new Supplier(objectId, 23, "SK TEX", new ArrayList<>(List.of("Salem")), new ArrayList<>(List.of("cotton")), new ArrayList<>(List.of(3)));

//         // Perform the POST request to "/suppliers"
//         mockMvc.perform(MockMvcRequestBuilders.post("/suppliers")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(new ObjectMapper().writeValueAsString(newSupplier)))
//                 .andExpect(status().isFound());  // Expect HTTP 302 Found for duplicate entry
//     }


//     @Test
//     void testGetSuppliers() throws Exception {
//         // Mock the service method to return a list of suppliers
//         ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
//         List<Supplier> mockSuppliers = Arrays.asList(
            
//             new Supplier(
//                 objectId,
//                 23, "SK TEX",
//                 new ArrayList<>(Arrays.asList("Salem")), 
//                 new ArrayList<>(Arrays.asList("cotton")), 
//                 new ArrayList<>(Arrays.asList(3)))
//             ,
//             new Supplier(
//                 objectId, 
//                 24,
//                 "KSK TEX", 
//                 new ArrayList<>(Arrays.asList("COVAI")), 
//                 new ArrayList<>(Arrays.asList("thread")), 
//                 new ArrayList<>(Arrays.asList(2))
//                 )
                
//         );
//         when(supplierService.getSuppliers()).thenReturn(mockSuppliers);

//         // Perform the GET request to "/suppliers"
//         mockMvc.perform(MockMvcRequestBuilders.get("/suppliers")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())  // Expect HTTP 200 OK
//                 .andExpect(jsonPath("$.length()").value(2))  // Adjust this based on the expected number of suppliers
//                 .andExpect(jsonPath("$[0].supplierId").value(23))  // Adjust these based on the expected data
//                 .andExpect(jsonPath("$[1].supplierId").value(24));
//     }


//     @Test
//     void testGetSupplierByIdFetched() throws Exception {
//         // Mock the service method to return a Supplier for a valid supplierId
//         int validSupplierId = 23;
//         ObjectId objectId = new ObjectId("65797bbac254a3759e242962");
//         Supplier mockSupplier = new Supplier(
//                 objectId,
//                 23, "SK TEX",
//                 new ArrayList<>(Arrays.asList("Salem")), 
//                 new ArrayList<>(Arrays.asList("cotton")), 
//                 new ArrayList<>(Arrays.asList(3)));
//         when(supplierService.getSupplierById(validSupplierId)).thenReturn(mockSupplier);

//         // Perform the GET request to "/suppliers/{supplierId}"
//         mockMvc.perform(MockMvcRequestBuilders.get("/suppliers/{supplierId}", validSupplierId)
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())  // Expect HTTP 200 OK
//                 .andExpect(jsonPath("$.supplierId").value(validSupplierId));  // Adjust based on the expected data
//     }

//      @Test
//     public void testDeleteSupplierById_Success() {
//         // Arrange
//         ObjectId supplierId = new ObjectId("65797bbac254a3759e242962");

//         // Mock the behavior of the service method
//         doNothing().when(supplierService).deleteSupplierById(supplierId);

//         // Act
//         supplierService.deleteSupplierById(supplierId);

//         // Verify that the service method was called with the correct argument
//         verify(supplierService, times(1)).deleteSupplierById(supplierId);
//     }

//     @Test
//     public void testDeleteSupplierById_Failure() {
//         // Arrange
//         ObjectId supplierId = new ObjectId("65797bbac254a3759e242962");

        
//         doNothing().when(supplierService).deleteSupplierById(supplierId);

//         // Act
//         // Call the method (even though it throws an exception)
//         try{
//         supplierService.deleteSupplierById(supplierId);
//         }
//         catch(Exception e){
//             e.getMessage();
//         }
        
//         // Verify that the service method was called with the correct argument
//         verify(supplierService, times(1)).deleteSupplierById(supplierId);
//     }

// }

   