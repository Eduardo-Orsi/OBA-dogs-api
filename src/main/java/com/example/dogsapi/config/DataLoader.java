package com.example.dogsapi.config;

import com.example.dogsapi.model.Dog;
import com.example.dogsapi.repository.DogRepository;
import com.example.dogsapi.model.User;
import com.example.dogsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final DogRepository dogRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String adminEmail;
    @Value("${app.admin.password}")
    private String adminPassword;

    public DataLoader(DogRepository dogRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.dogRepository = dogRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Seed SUPER_ADMIN user if not present
        if (userRepository.findByUsername(adminEmail).isEmpty()) {
            User admin = new User(adminEmail, passwordEncoder.encode(adminPassword), "SUPER_ADMIN");
            userRepository.save(admin);
        }
    }
} 