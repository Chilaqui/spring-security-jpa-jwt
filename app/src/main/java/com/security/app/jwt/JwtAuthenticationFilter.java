package com.security.app.jwt;

import java.io.IOException;
import java.rmi.ServerException;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.security.app.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
// Importante: Asegurarse de que la clase JwtAuthenticationFilter sea un componente de
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    // Aqui se implementa la logica del filtro de autetificacion JWT
    // Este filtro se encargara de interceptar las peticiones HTTP y verificar el token JWT
    // Si el token es valido, se permitira el acceso al recurso solicitado
    // Si el token no es valido o no esta presente, se rechazara la peticion y se devolvera un error 401 Unauthorized

    @Autowired 
    // Inyectamos el servicio de utilidades JWT
    private JwtUtils jwtUtils;

    @Autowired
    // Inyectamos el servicio de detalles de usuario personalizado
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)throws ServerException, IOException{



                                    }




}
