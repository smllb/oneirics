package com.lucid.oneiric.security;

import com.lucid.oneiric.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal  implements UserDetails {
    private UserEntity userEntity;

    public UserPrincipal(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        // i could just wrap this in return but i'll make it clear just for the sake of better reading
        return  authorities;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }


    public String getId() { return userEntity.getId(); }

    @Override
    public String getUsername() {
        return userEntity.getLogin();
    }

    public String getEmail() { return userEntity.getEmail(); }
    @Override
    public boolean isAccountNonExpired() {
        return userEntity.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userEntity.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userEntity.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return userEntity.isEnabled();
    }
}
