package com.lucid.oneiric.controller;

import com.lucid.oneiric.security.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProfileController {

    @GetMapping("/profile")
    public ResponseEntity<Object> getUserProfileInformation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return ResponseEntity.ok().body(new Object() {
            public final String username = userPrincipal.getUsername();
            public final String email = userPrincipal.getEmail();

        });
    }

    @GetMapping("/isLoggedIn")
    public ResponseEntity<Map<String, String>> getUserAuthenticationStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> response = new HashMap<>();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            response.put("message", "User authenticated.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "User not authenticated.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}