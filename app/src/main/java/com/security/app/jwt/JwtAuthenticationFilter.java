package com.security.app.jwt;

import java.io.IOException;
import java.rmi.ServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

        // 1. Obtenemos el token JWT del encabezado de la solicitud
        String authHeader = request.getHeader("Authorization");

        // 2. Verificamos si el token esta presente y comienza con "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwtToken = authHeader.substring(7);// Extraemos el token sin el prefijo "Bearer "
            String username = jwtUtils.extracUsername(jwtToken);// Extraemos el nombre de usuario del token

            // 3. Verificamos que el no este autentificaco todabia
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                
                // 4. Cargamos los detalles del usuario usando el servicio personalizado
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                // 5. Validamos el token con ese usuario
                if (jwtUtils.validarToken(jwtToken, userDetails)) {

                    
                }
            
            }
        }

        


                                    }




}
