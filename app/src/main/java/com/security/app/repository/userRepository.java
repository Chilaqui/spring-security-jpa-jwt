package com.security.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.app.model.User;

public interface userRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUserName(String userName);

}
