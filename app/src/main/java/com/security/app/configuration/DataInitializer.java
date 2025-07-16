package com.security.app.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.app.model.Role;
import com.security.app.model.User;
import com.security.app.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            if (userRepository.count() == 0) { // solo si no hay usuarios
                User admin = new User();
                admin.setUserName("admin");
                admin.setPassword(passwordEncoder.encode("123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);

                User developer = new User();
                developer.setUserName("dev");
                developer.setPassword(passwordEncoder.encode("123"));
                developer.setRole(Role.DEVELOPER);
                userRepository.save(developer);

                User manager = new User();
                manager.setUserName("manager");
                manager.setPassword(passwordEncoder.encode("123"));
                manager.setRole(Role.MANAGER);
                userRepository.save(manager);

                User user = new User();
                user.setUserName("user");
                user.setPassword(passwordEncoder.encode("123"));
                user.setRole(Role.USER);
                userRepository.save(user);

                System.out.println("🟢 Usuarios iniciales insertados correctamente.");
            } else {
                System.out.println("🟡 Ya existen usuarios, no se insertaron datos.");
            }
        };
    }

}
