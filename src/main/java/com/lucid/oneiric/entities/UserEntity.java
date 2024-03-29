package com.lucid.oneiric.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private String id;
    private String login;
    private String password;
    private String email;
    @Column(name = "recovery_email")
    String recoveryEmail;

    protected UserEntity() {

    }

    public UserEntity(String login, String password, String email) {
        this.id = UUID.randomUUID().toString();;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public UserEntity(String id, String login, String password, String email, String recoveryEmail) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.recoveryEmail = recoveryEmail;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void addRole(RoleEntity role) {
        roles.add(role);
    }
    public void removeRole(RoleEntity role) {
        roles.remove(role);
    }

    public UserEntity(String login, String password, String email, String recoveryEmail) {
        this.id = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        this.email = email;
        this.recoveryEmail = recoveryEmail;
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

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRecoveryEmail() {
        return recoveryEmail;
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

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }
    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

}