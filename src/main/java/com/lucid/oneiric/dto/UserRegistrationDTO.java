package com.lucid.oneiric.dto;

public class UserDTO {
    String login, password, email, recoveryEmail;

    public UserDTO(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public UserDTO(String login, String password, String email, String recovery_email) {
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
}
