package com.example.springboot.service;

import com.example.springboot.entity.Transaction;

public interface TransactionService {

    boolean saveTransaction(Transaction transaction);
    
}
