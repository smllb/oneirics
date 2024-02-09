package com.lucid.oneiric.services;

import com.lucid.oneiric.dto.UserRegistrationDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UsersService {

    private final RegisterService registerService;


    public UsersService(RegisterService registerService) {
        this.registerService = registerService;
    }

    public void registerNewUser(UserRegistrationDTO userRegistrationDTO) {
        registerService.register(userRegistrationDTO);

    }
}
