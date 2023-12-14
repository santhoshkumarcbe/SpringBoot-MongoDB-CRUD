package com.example.demo.controller;
import java.util.*;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
@RestController
public class ctrl{
	@GetMapping("/")
	String egcontroller() {
		return "Hello";
	}
	
	@GetMapping(value={"/val"})
	List<String> eg2controller(@RequestParam(required=false,name="users") List<String> name) {
		return name;
	}
	
	@GetMapping(value={"/hello","/hello/{id}","/hello/"})
	String eg1controller(@PathVariable(required=false) String id) {
		return "Hello "+id;
	}
}