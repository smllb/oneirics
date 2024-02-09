package com.lucid.oneiric.controller;

import com.lucid.oneiric.dto.UserRegistrationDTO;
import com.lucid.oneiric.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final UsersService usersService;

    public RegisterController(UsersService usersService) {
        this.usersService = usersService;

    }

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDto) {
        System.out.println("register request:");
        System.out.println("Login: " + userRegistrationDto.getLogin());
        System.out.println("Password: " + userRegistrationDto.getPassword());
        System.out.println("Email: " + userRegistrationDto.getEmail());
        System.out.println("Recovery Email: " + userRegistrationDto.getRecoveryEmail());

        usersService.registerNewUser(userRegistrationDto);
        return ResponseEntity.ok("Registration successful");
    }

}
