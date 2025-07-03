package com.security.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.security.app.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //Register
    @Override
    public ResponseEntity<String> registerUser (User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User Resgistered successfully");
    }

    // Login
    @Override
    public String login(User user){
        Optional<User> extingUser = userRepository.findByUserName(user.getUserName());
        if (extingUser.isEmpty() || !passwordEncoder.matches(user.getPassword(), extingUser.get().getPassword())) {
            return "Invalid username or password";
        }
        return "Login successful";

    }

}


