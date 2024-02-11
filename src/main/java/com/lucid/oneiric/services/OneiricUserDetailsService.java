package com.lucid.oneiric.services;

import com.lucid.oneiric.entities.UserEntity;
import com.lucid.oneiric.repository.UsersRepository;
import com.lucid.oneiric.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OneiricUserDetailsService implements UserDetailsService {

    private UsersRepository usersRepository;
    @Autowired
    public OneiricUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserEntity> UserQueryResult = usersRepository.findByLogin(username);
        if (UserQueryResult.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        UserEntity user = UserQueryResult.get(0);
        return new UserPrincipal(user);

    }
}
