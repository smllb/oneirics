package com.lucid.oneiric.repository;

import com.lucid.oneiric.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository

public interface UsersRepository extends JpaRepository<UserEntity, String> {

    List<UserEntity> findByLogin(String login);
    List<UserEntity> findByEmail(String email);
    List<UserEntity> findByRecoveryEmail(String recoveryEmail);

    default void deleteByUsername(String login) {
        List<UserEntity> loginQueryResult = findByLogin(login);
        if (loginQueryResult.isEmpty()) {
            throw new NoSuchElementException();
        }

        deleteById(loginQueryResult.get(0).getId());
        System.out.println("delet success");
    }


}
