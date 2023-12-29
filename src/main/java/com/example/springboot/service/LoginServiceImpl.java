package com.example.springboot.service;

import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl {
    public String generateToken(String username,String pwd)
    {
        Claims claims=Jwts.claims().setSubject(username);
        System.out.println("Claims :"+claims);
        String token=Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256,"*U(8hj908ns98daniasudfniawur97q2e7r2934892rnu213rn09217349782190348y12").compact();
        System.out.println("Token: "+token);
        return token;
    }
    public String validateToken(String token,String name){
        if(token.equals(generateToken(name, ""))){
            return "Valid";
        }
        return "Invalid";
    }
}
