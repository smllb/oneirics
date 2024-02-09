package com.lucid.oneiric.services;

import com.lucid.oneiric.dto.UserRegistrationDTO;
import com.lucid.oneiric.exceptions.MissingEmailDataException;
import com.lucid.oneiric.exceptions.MissingLoginDataException;
import com.lucid.oneiric.exceptions.MissingMultipleRequiredFieldsException;
import com.lucid.oneiric.exceptions.MissingPasswordDataException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;

@Service
public class UsersService {

    private final RegisterService registerService;


    public UsersService(RegisterService registerService) {
        this.registerService = registerService;
    }

    public ResponseEntity<String> registerNewUser(UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            if (bindingResult.getFieldErrors().size() > 2) {
                throw new MissingMultipleRequiredFieldsException();
            }
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                String fieldName = fieldError.getField();
                switch (fieldName) {
                    case "login":
                        throw new MissingLoginDataException();
                    case "password":
                        throw new MissingPasswordDataException();
                    case "email":
                        throw new MissingEmailDataException();
                }
            }
            return ResponseEntity.badRequest().body("Unknown invalid request.");
        }

        return registerService.register(userRegistrationDTO);

    }
}
