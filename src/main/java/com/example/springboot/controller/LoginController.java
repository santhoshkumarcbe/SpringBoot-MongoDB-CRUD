package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import jakarta.servlet.http.HttpServletRequest;

public class LoginController {
     @Autowired
    loginservice loginservice;
    @PutMapping("/{username}/{pwd}") 
    String key(@PathVariable String username,@PathVariable String pwd)
    {
        return service.generateToken(username,pwd);
    }
      @GetMapping("/verify/{name}")
    public String getMethodName(HttpServletRequest request, @PathVariable String name) {
        return service.validateToken(request.getHeader("Authorization").split(" ",2)[1],name);
    }
}
