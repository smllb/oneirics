package com.lucid.oneiric.services;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurePasswordService {

    private final PasswordEncoder passwordEncoder;


    public SecurePasswordService() {
        this.passwordEncoder= new Argon2PasswordEncoder(16, 32, 1, 4096, 3);

    }
    public String encodeRawPassword(String rawPassword) {

        return passwordEncoder.encode(rawPassword);

    }
}
