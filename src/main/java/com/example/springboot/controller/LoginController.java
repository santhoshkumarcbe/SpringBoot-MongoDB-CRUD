package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springboot.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/login")

public class LoginController {
     @Autowired
    LoginService loginservice;
    @PutMapping("/{username}/{pwd}") 
    String key(@PathVariable String username,@PathVariable String pwd)
    {
        return loginservice.generateToken(username,pwd);
    }
      @GetMapping("/verify/{name}")
    public String getMethodName(HttpServletRequest request, @PathVariable String name) {
        return loginservice.validateToken(request.getHeader("Authorization").split(" ",2)[1],name);
    }
}
