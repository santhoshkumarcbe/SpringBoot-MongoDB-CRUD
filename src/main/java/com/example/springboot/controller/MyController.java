package com.example.springboot.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController{
	// @Autowired 

	@GetMapping("/")
	public String HelloWorld() {
		return "Spring Boot Controller Welcomes you !!!";
		
	}
	}
	
