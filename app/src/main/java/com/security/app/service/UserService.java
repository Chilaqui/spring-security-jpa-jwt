package com.security.app.service;

import org.springframework.http.ResponseEntity;

import com.security.app.model.User;

public interface UserService {

    ResponseEntity<String> registerUser(User  user);

    String login(User user);

}
