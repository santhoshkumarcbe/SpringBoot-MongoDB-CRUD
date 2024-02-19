package com.example.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.springboot.errorHandling.supplierNotFoundError;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value=supplierNotFoundError.class)
    // @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleSupplierNotFound(supplierNotFoundError e){
        return new ResponseEntity<>("Supplier not found:" + e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
