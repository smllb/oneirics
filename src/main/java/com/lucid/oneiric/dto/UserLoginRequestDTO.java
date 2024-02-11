package com.lucid.oneiric.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserLoginRequestDTO {
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;

    public UserLoginRequestDTO() {

    }

    public UserLoginRequestDTO(String login, String password) {
        this.username = login;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
