package com.security.app.service.auth;

// Interface (contrato)
public interface AuthService {

    //El método tiene una única responsabilidad: autenticar al usuario y devolver un token JWT.
    String loginJwt(String username, String password);

    // Método para registrar un nuevo usuario
    // Este método recibe un nombre de usuario y una contraseña, y devuelve un token JWT si
    String registerJwt(String username, String password, String role); 

}
