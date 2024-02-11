package com.lucid.oneiric.controller;

import com.lucid.oneiric.dto.UserLoginRequestDTO;
import com.lucid.oneiric.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

    }

    @PostMapping("/login")
    public ResponseEntity<String> LogUserIn(@RequestBody UserLoginRequestDTO userLoginRequestDTO, HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationRequest = new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        SecurityContextHolder.getContext().setAuthentication(authenticationResponse);

        HttpSessionSecurityContextRepository repository = new HttpSessionSecurityContextRepository();
        repository.saveContext(SecurityContextHolder.getContext(), request, response);

        return ResponseEntity.ok().body(String.format("User %s logged in.", userLoginRequestDTO.getUsername()));

    }

}
