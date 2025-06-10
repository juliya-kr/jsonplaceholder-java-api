package com.example.jsonplaceholder.repository;

import com.example.jsonplaceholder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
} 