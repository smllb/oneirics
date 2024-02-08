package com.lucid.oneiric.controller;

import com.lucid.oneiric.dto.UserDTO;
import com.lucid.oneiric.entities.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    UserDTO userData;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registerUser(UserDTO userDto) {

//        UserEntity userEntity = new UserEntity();
    }

}
