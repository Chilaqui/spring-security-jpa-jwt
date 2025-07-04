package com.security.app.jwt;

import java.security.Key;
import java.security.Signature;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {

    private static final Key SECRET_KEY = Keys.hmacShaKeyFor("mi_clave_secreta_larga_y_constante".getBytes());

    public String generateToken(UserDetails userDetails){
        return null;
    }

    public boolean validarToken(String token,UserDetails userDetails){

        return false;
    }

    public String extracUsername(String token){

        return null;
    }

}
