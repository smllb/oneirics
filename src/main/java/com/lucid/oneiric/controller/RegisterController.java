package com.lucid.oneiric.controller;

import com.lucid.oneiric.dto.UserRegistrationDTO;
import com.lucid.oneiric.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final UsersService usersService;
    @Autowired

    public RegisterController(UsersService usersService) {
        this.usersService = usersService;

    }

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDto, BindingResult bindingResult) {
        return usersService.registerNewUser(userRegistrationDto, bindingResult);

    }


}