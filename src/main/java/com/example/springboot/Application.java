package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
		// Supplier supplier = new Supplier(1,"ABC LIMITED","Ahamedabad","NA",2);
		// log.info("Supplier: {}", supplier);
	}

}
