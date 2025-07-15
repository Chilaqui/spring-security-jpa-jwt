package com.security.app.dto;

public class LoginResponse {
// Esta clase sirve para enviar el token JWT como respuesta al login
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
