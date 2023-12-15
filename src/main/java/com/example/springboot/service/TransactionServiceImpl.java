package com.example.springboot.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entity.Supplier;
import com.example.springboot.entity.Transaction;
import com.example.springboot.repository.SupplierRepository;
import com.example.springboot.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    public boolean saveTransaction(Transaction transaction) {
        boolean isSupplierId=supplierRepository.existsBySupplierId(transaction.getSupplierId());
        boolean isSupplierName=supplierRepository.existsBySupplierName(transaction.getSupplierName());
        boolean isTransactionSupplierId=transactionRepository.existsBySupplierId(transaction.getSupplierId());
        boolean isTransactionSupplierName=transactionRepository.existsBySupplierName(transaction.getSupplierName());
        if (isSupplierId  && isSupplierName && !isTransactionSupplierId && !isTransactionSupplierName) {
            Supplier supplier=supplierRepository.findBySupplierId(transaction.getSupplierId());
            ArrayList<Integer> transactionList=new ArrayList<>();
            transactionList.add(transaction.getPrice());
            transaction.setTransactionList(transactionList);
            transaction.setTotalPrice(transaction.getPrice());
        }
        Transaction ExsitingTransaction=transactionRepository.findBySupplierId(transaction.getSupplierId());

        transactionRepository.save(transaction);
    }
    
}
