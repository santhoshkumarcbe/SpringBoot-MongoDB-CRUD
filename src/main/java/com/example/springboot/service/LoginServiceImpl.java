package com.example.springboot.service;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginServiceImpl implements LoginService{
    public String generateToken(String username,String pwd)
    {
        Claims claims=Jwts.claims().setSubject(username);
        System.out.println("Claims :"+claims);
        String token=Jwts.builder().setClaims(claims).
        signWith(SignatureAlgorithm.HS256,"*U(8hj908ns98daniasudfniawur97q2e7r2934892rnu213rn09217349782190348y12").compact();
        System.out.println("Token: "+token);
        return token;
    }
    public boolean validateToken(String token,String name){
        if(token.equals(generateToken(name, ""))){
            return true;
        }
        return false;
    }
    @Override
    public String extractToken(String authorizationHeader) {
        if (authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
