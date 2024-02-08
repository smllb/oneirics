package com.lucid.oneiric.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String login, salt, password, email, recovery_email;
    private Integer account_type_id;

    protected UserEntity() {}

    public UserEntity(UUID id, String login, String salt, String password, String email, Integer account_type_id) {
        this.id = id;
        this.login = login;
        this.salt = salt;
        this.password = password;
        this.email = email;
        this.account_type_id = account_type_id;
    }

    public UserEntity(UUID id, String login, String salt, String password, String email, String recovery_email, Integer account_type_id) {
        this.id = id;
        this.login = login;
        this.salt = salt;
        this.password = password;
        this.email = email;
        this.recovery_email = recovery_email;
        this.account_type_id = account_type_id;
    }

    @Override
    public String toString() {
        return String.format("UsersEntity[id=%s, login='%s', email='%s']", id, login, email);
    }

    public UUID getId() {
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

    public String getRecovery_email() {
        return recovery_email;
    }

    public Integer getAccount_type_id() {
        return account_type_id;
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

    public void setRecovery_email(String recovery_email) {
        this.recovery_email = recovery_email;
    }

    public void setAccount_type_id(Integer account_type_id) {
        this.account_type_id = account_type_id;
    }
}
