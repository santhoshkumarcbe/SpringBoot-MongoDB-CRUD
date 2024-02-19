package com.example.springboot.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController{
	// @Autowired 

	@GetMapping("/")
	public String HelloWorld() {
		return "Spring Boot Controller Welcomes you !!!";
	}
	@GetMapping("/demo")
	public ResponseEntity<String> greet() {
		return ResponseEntity.ok("Welcome");
	}
	
	}
	
