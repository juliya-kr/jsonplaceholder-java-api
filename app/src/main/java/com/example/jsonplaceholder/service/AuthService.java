package com.example.jsonplaceholder.service;

import com.example.jsonplaceholder.model.AuthUser;
import com.example.jsonplaceholder.repository.AuthUserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {
    private final AuthUserRepository authUserRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${jwt.secret:defaultSecretKey}")
    private String jwtSecret;

    public AuthService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    public AuthUser register(String name, String email, String password) {
        String hash = passwordEncoder.encode(password);
        AuthUser user = AuthUser.builder().name(name).email(email).passwordHash(hash).build();
        return authUserRepository.save(user);
    }

    public Optional<AuthUser> authenticate(String email, String password) {
        Optional<AuthUser> userOpt = authUserRepository.findByEmail(email);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPasswordHash())) {
            return userOpt;
        }
        return Optional.empty();
    }

    public String generateToken(AuthUser user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("name", user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes())
                .compact();
    }
} 