package com.lucid.oneiric.controller;

import com.lucid.oneiric.dto.UserPublicViewDTO;
import com.lucid.oneiric.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProfileController {
    private final UsersService usersService;

    public ProfileController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("user/{username}/profile")
    public ResponseEntity<Object> getUserProfileInformation(@PathVariable String username) {
        try {
            UserPublicViewDTO user = usersService.getUserPublicView(username);

            return ResponseEntity.ok(user);

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/user/isactive")
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