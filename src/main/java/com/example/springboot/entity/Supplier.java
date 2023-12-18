package com.example.springboot.entity;

import java.util.ArrayList;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "supplier")

public class Supplier{
	@Id
	private ObjectId id;
	private int supplierId;
	private String supplierName;
	private ArrayList<String> location;
	private ArrayList<String> materialType;
	private ArrayList<Integer> tier;

}