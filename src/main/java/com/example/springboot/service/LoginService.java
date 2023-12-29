package com.example.springboot.service;

public interface LoginService {

    String generateToken(String username, String pwd);

    String validateToken(String string, String name);
    
}
