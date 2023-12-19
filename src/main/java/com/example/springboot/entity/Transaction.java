package com.example.springboot.entity;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

@Document(collection="transaction")

public class Transaction {
    @Id
    private ObjectId id;
    private int supplierId;
    private String supplierName;
    private int price;
    private ArrayList<Integer> transactionList;
    private int totalPrice;
    
}
