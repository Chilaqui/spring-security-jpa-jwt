package com.security.app.configuration;

import java.net.http.HttpClient;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/register").hasAnyRole("DEVELOER")
                .anyRequest().authenticated()

            )
            .httpBasic(Customizer.withDefaults())
            .formLogin(form -> form.disable());
    }
}
