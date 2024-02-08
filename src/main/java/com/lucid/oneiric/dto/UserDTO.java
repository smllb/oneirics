package com.lucid.oneiric.dto;

public class UserDTO {
    String username, password, email, recovery_email;

    public UserDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserDTO(String username, String password, String email, String recovery_email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.recovery_email = recovery_email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRecovery_email() {
        return recovery_email;
    }
}
