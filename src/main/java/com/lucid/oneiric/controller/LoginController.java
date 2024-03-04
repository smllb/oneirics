package com.lucid.oneiric.controller;

import com.lucid.oneiric.dto.UserLoginRequestDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {
    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

    }

    @PostMapping("/user/login")
    public ResponseEntity<String> LogUserIn(@RequestBody UserLoginRequestDTO userLoginRequestDTO, HttpServletRequest request, HttpServletResponse response) {
        try {
            UsernamePasswordAuthenticationToken authenticationRequest = new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword());
            Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
            SecurityContextHolder.getContext().setAuthentication(authenticationResponse);

            HttpSessionSecurityContextRepository repository = new HttpSessionSecurityContextRepository();
            repository.saveContext(SecurityContextHolder.getContext(), request, response);
            CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

            if (csrfToken != null) {
                Cookie cookie = new Cookie("XSRF-TOKEN", csrfToken.getToken());
                cookie.setPath("/");
                cookie.setSecure(true);
                cookie.setHttpOnly(false);
                response.addCookie(cookie);
            }
            return ResponseEntity.ok().body(String.format("User %s logged in.", userLoginRequestDTO.getUsername()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid credentials.");
        }

    }

    @GetMapping("/csrf")
    @CrossOrigin
    public ResponseEntity<String> getCsrfToken(HttpServletRequest request) {
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        return ResponseEntity.ok().body(csrf.getToken());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {

    }

}
