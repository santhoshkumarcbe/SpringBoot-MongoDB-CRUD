package com.example.springboot.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        int transactionLimit=1000;
        boolean isSupplierId=supplierRepository.existsBySupplierId(transaction.getSupplierId());
        boolean isSupplierName=supplierRepository.existsBySupplierName(transaction.getSupplierName());
        boolean isTransactionSupplierId=transactionRepository.existsBySupplierId(transaction.getSupplierId());
        boolean isTransactionSupplierName=transactionRepository.existsBySupplierName(transaction.getSupplierName());
        if (isSupplierId  && isSupplierName && !isTransactionSupplierId && !isTransactionSupplierName) {
            ArrayList<Integer> transactionList=new ArrayList<>();
            transactionList.add(transaction.getPrice());
            transaction.setTransactionList(transactionList);
            if (transaction.getPrice() <= transactionLimit) {
                transaction.setTotalPrice(transaction.getPrice());
            }
            transactionRepository.save(transaction);
            return true;
        }
        else if (isSupplierId  && isSupplierName && isTransactionSupplierId && isTransactionSupplierName) {
            Transaction ExsitingTransaction=transactionRepository.findBySupplierId(transaction.getSupplierId());
            int totalPrice=ExsitingTransaction.getTotalPrice();
            totalPrice+=transaction.getPrice();
            if (totalPrice < transactionLimit) {
                ArrayList<Integer> transactionList=ExsitingTransaction.getTransactionList();
                transactionList.add(transaction.getPrice());
                ExsitingTransaction.setTransactionList(transactionList);
                ExsitingTransaction.setTotalPrice(totalPrice);
                transactionRepository.save(ExsitingTransaction);
                return true;
            }
            else{
                return false;
            }
        } else {
            return false;
        }
    }
    
}
