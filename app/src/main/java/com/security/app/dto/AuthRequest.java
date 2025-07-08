package com.security.app.dto;

// Esta clase sirve para recibir el JSON del login
public class AuthRequest {

    private String username;
    private String Password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }

}
