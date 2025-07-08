package com.security.app.service.auth;

// Interface (contrato)
public interface AuthService {

    //El método tiene una única responsabilidad: autenticar al usuario y devolver un token JWT.
    String login(String username, String password);
}
