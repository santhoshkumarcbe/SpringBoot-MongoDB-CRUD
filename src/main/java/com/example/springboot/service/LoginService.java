package com.example.springboot.service;

public interface LoginService {

    String generateToken(String username, String pwd);

    boolean validateToken(String string, String name);

    String extractToken(String authorizationHeader);
    
}
