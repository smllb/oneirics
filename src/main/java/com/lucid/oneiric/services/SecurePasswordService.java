package com.lucid.oneiric.services;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class SecurePasswordService {

    private final PasswordEncoder passwordEncoder;


    public SecurePasswordService() {
        this.passwordEncoder= new Argon2PasswordEncoder(16, 32, 1, 4096, 3);

    }
    public HashMap<String, String> encodeRawPassword(String rawPassword) {
        String encodedPassword =  passwordEncoder.encode(rawPassword);
        String[] passwordParts = encodedPassword.split("\\$");

        HashMap<String, String> passwordData = new HashMap<>();
        passwordData.put("salt", passwordParts[4]);
        passwordData.put("hash", passwordParts[5]);

        return passwordData;

    }
}
