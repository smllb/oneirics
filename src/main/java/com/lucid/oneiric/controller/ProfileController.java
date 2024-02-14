package com.lucid.oneiric.controller;

import com.lucid.oneiric.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> getUserAuthenticationStatus() {
            return ResponseEntity.ok().body("User authenticated.");
        }

}
