package com.security.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.app.model.User;
import com.security.app.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {
   
    @Autowired
    private UserRepository userRepository;

    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado con el username: " + userName);
        }
        System.out.println("User found: " + user.get().getUserName());
        System.out.println("Authorities: " + user.get().getAuthorities());

        return user.get();

    }
}
