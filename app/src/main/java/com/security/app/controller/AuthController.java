package com.security.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.security.app.dto.LoginRequest;
import com.security.app.service.auth.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService; // Inyectamos el servicio de autenticación con JWT


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        //Se llama al servicio para autenticar y generar el JWT
        String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());//Se llama al dto para que podamos recibir JSON
        return ResponseEntity.ok(token);//Devuelve el token como respuesta
    }

}
