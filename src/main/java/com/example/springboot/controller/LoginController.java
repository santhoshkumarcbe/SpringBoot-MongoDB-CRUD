package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.service.LoginService;


@RestController
@RequestMapping("/login")

public class LoginController {
     @Autowired
    LoginService loginservice;
    @PostMapping("/{username}/{pwd}") 
    String key(@PathVariable String username,@PathVariable String pwd)
    {
        return loginservice.generateToken(username,pwd);
    }
    
    @GetMapping("verify/{username}")
    public ResponseEntity<String> tokenValidation(@RequestHeader("Authorization") String authorizationHeader ,@PathVariable String username ){
        String token=loginservice.extractToken(authorizationHeader);
        if (loginservice.validateToken(token, username)) {
            return ResponseEntity.ok("Valid token");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }
}
