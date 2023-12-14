package com.example.springboot.entity;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "supplier")

public class Supplier{
	@Id
	private Object id;
	private int supplierId;
	private String supplierName;
	private ArrayList<String> location;
	private String materialType;
	private int tier;

}