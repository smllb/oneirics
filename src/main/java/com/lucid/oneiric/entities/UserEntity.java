package com.lucid.oneiric.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private String id;
    private String login, salt, password, email;
    @Column(name = "recovery_email")
    String recoveryEmail;
    @Column(name = "account_type_id")
    private Integer accountTypeId;

    protected UserEntity() {

    }

    public UserEntity(String login, String salt, String password, String email, Integer accountTypeId) {
        this.id = UUID.randomUUID().toString();;
        this.login = login;
        this.salt = salt;
        this.password = password;
        this.email = email;
        this.accountTypeId = accountTypeId;
    }

    public UserEntity(String login, String salt, String password, String email, String recoveryEmail, Integer accountTypeId) {
        this.id = UUID.randomUUID().toString();
        this.login = login;
        this.salt = salt;
        this.password = password;
        this.email = email;
        this.recoveryEmail = recoveryEmail;
        this.accountTypeId = accountTypeId;
    }

    @Override
    public String toString() {
        return String.format("UsersEntity[id=%s, login='%s', email='%s']", id, login, email);
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSalt() {
        return salt;
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

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public void setAccountTypeId(Integer accountTypeId) { this.accountTypeId = accountTypeId; }
}