package com.example.jsonplaceholder.service;

import com.example.jsonplaceholder.model.User;
import com.example.jsonplaceholder.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserService userService;

    public DatabaseSeeder(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = new ClassPathResource("users-seed.json").getInputStream();
        List<User> users = mapper.readValue(inputStream, new TypeReference<List<User>>() {});
        for (User user : users) {
            userService.save(user);
        }
    }
} 