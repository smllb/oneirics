package com.lucid.oneiric.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

public class UserRegistrationDTO implements Serializable {

    @NotBlank(message = "Login cannot be blank.")
    @NotNull(message = "Login can't be null.")
    private String login;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    @NotNull
    private String email;


    private String recoveryEmail;

    public UserRegistrationDTO() {

    }
    public UserRegistrationDTO(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public UserRegistrationDTO(String login, String password, String email, String recovery_email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.recoveryEmail = recovery_email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRecoveryEmail() {
        return recoveryEmail;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRecoveryEmail(String recoveryEmail) {
        this.recoveryEmail = recoveryEmail;
    }
}
