package com.security.app.jwt;

import java.security.Key;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {

    //Forma basica declarada(Con posibilidad de hacerlo en una variable de entorno segura)
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor("mi_clave_secreta_larga_y_constante".getBytes());

    public String generateToken(UserDetails userDetails){
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + 1000 * 60 * 60); // 1 hora de expiracion 

        //Generacion de token usundo el nombre del usuario

        return Jwts.builder()
            .setSubject(userDetails.getUsername())//Nobre de el usuario
            .setIssuedAt(issuedAt)//Fecha actual
            .setExpiration(expiration)//Una espiracion
            .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
            .compact();
    }

    public String extracUsername(String token){

        return Jwts.parserBuilder() //Como se debe de leer y verificar un token
                .setSigningKey(SECRET_KEY)//Defino clave secreta con la que se firma el token
                .build()//Finaliza la contruccion
                .parseClaimsJws(token)//Token se analiza y valida (Verifica la firma, verifica caducacion, devulve un objeto Jws<Claims> contiene el cuerpo del token)
                .getBody()//Obtiene solo el contenido de (claims) del token que es donde estan tus datos
                .getSubject();//El subject es el nombre del usuario(Se esta recuperando)

    }

    

}
