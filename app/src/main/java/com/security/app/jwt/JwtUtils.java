package com.security.app.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public class JwtUtils {

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
