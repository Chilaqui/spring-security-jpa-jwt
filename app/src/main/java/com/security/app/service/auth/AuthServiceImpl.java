package com.security.app.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.security.app.jwt.JwtUtils;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authManager;//Se encarga de autetifiacar usuarios(Recibe usuario, contraseña y valida si existen)

    @Autowired
    private JwtUtils jwtUtils;//Servicio mio que genera los tokens

    //Este es el método principal que implementa la lógica del login.Recibe el username y la password desde el cliente (Postman, frontend, etc.).
    @Override
    public String login(String username,String password){

        // 1. Autenticar usuario usando Spring Security
        //Llama a authManager.authenticate(...), que revisa el usuario y la contraseña usando tu UserDetailsService.
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));//Crea un objeto UsernamePasswordAuthenticationToken, que Spring usa para representar un intento de login.

        // 2. Obtener los detalles del usuario autenticado
        //Si el usuario fue autenticado correctamente, Spring devuelve un objeto Authentication.
        UserDetails userDetails =(UserDetails) authentication.getPrincipal();//El getPrincipal() contiene los datos del usuario (que tú cargaste desde la base de datos).

        // 3. Generar un token JWT y devolverlo
        //Usamos la clase JwtUtils para generar un token JWT con la información del usuario.
        return jwtUtils.generateToken(userDetails);


    }

}
