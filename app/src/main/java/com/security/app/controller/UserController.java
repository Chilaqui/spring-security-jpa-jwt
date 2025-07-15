package com.security.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.app.dto.AuthRequest;
import com.security.app.model.User;
import com.security.app.service.UserService;
import com.security.app.service.auth.AuthService;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;// Inyectamos el servicio de autenticación con JWT

    @PreAuthorize("hasRole('DEVELOPER')")
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    //Cambiando el login simple por la integracion de JWT
    /* @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        return ResponseEntity.ok(userService.login(user));
    } */

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest){
        //Se llama al servicio para autenticar y generar el JWT
        String token = authService.login(authRequest.getUsername(), authRequest.getPassword());//Se llama al dto para que podamos recibir JSON
        return ResponseEntity.ok(token);//Devuelve el token como respuesta
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<String> user(){
        return ResponseEntity.ok("Hola Userbienvenido a las nuevas reglas");
    }

    @PreAuthorize("hasRole('DEVELOPER')")
    @GetMapping("/developer")
    public ResponseEntity<String> developer(){
        return ResponseEntity.ok("Hola Developer, entraste con tu token JWT");
    }
}
