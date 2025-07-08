package com.security.app.jwt;

import java.security.Key;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {

    // Forma basica declarada(Con posibilidad de hacerlo en una variable de entorno
    // segura)
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor("mi_clave_secreta_larga_y_constante".getBytes());

    public String generateToken(UserDetails userDetails) {
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + 1000 * 60 * 60); // 1 hora de expiracion

        // Generacion de token usundo el nombre del usuario

        return Jwts.builder()
                .setSubject(userDetails.getUsername())// Nobre de el usuario
                .setIssuedAt(issuedAt)// Fecha actual
                .setExpiration(expiration)// Una espiracion
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extracUsername(String token) {

        return Jwts.parserBuilder() // Como se debe de leer y verificar un token
                .setSigningKey(SECRET_KEY)// Defino clave secreta con la que se firma el token
                .build()// Finaliza la contruccion
                .parseClaimsJws(token)// Token se analiza y valida (Verifica la firma, verifica caducacion, devulve un
                                      // objeto Jws<Claims> contiene el cuerpo del token)
                .getBody()// Obtiene solo el contenido de (claims) del token que es donde estan tus datos
                .getSubject();// El subject es el nombre del usuario(Se esta recuperando)

    }

    // Métodos para validar token

    // Junta todo: valida que el usuario coincida y que el token no haya expirado.
    public boolean validarToken(String token, UserDetails userDetails) {
        final String username = extracUsername(token); // Extrae el nombre de usuario del token
        return (username.equals(userDetails.getUsername()) && !isTokenEspired(token));
        // username.equals(...) -> Se asegura que el token pertenece al usuario que dice
        // ser.
        // !isTokenExpired(token) -> Verifica que el token aún no ha caducado.
    }

    // Comprueba si el token ha expirado comparando la fecha de expiración con la
    // fecha actual.
    private boolean isTokenEspired(String token) {
        return extractEspiration(token).before(new Date());
        // Si la expiración es anterior a la fecha actual, el token ya expiró (retorna
        // true).
    }

    // Obtiene la fecha de expiración del token.
    private Date extractEspiration(String token) {
        return extractAlClaims(token).getExpiration();
        // Llama a extractAllClaims y obtiene la fecha de expiración desde los "claims".
    }

    // Extrae todos los claims (datos) del JWT. Un claim es un dato dentro del
    // token.
    private Claims extractAlClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // Se establece la clave secreta usada para firmar el token.
                .build() // Se construye el parser.
                .parseClaimsJws(token) // Se valida y analiza el token (firma, expiración, etc.).
                .getBody(); // Se obtienen solo los datos del cuerpo del token (claims).
    }

}
