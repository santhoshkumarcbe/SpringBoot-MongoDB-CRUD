package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entity.Transaction;
import com.example.springboot.service.TransactionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction) {
        if (transactionService.saveTransaction(transaction)) {
            return new ResponseEntity<Transaction>(transaction,HttpStatus.OK);
        }
        
        return new ResponseEntity<Transaction>(transaction,HttpStatus.NOT_FOUND);
    }
    
}
