package com.lucid.oneiric.repository;

import com.lucid.oneiric.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UsersRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByAccountTypeId(Integer accountTypeId);

    List<UserEntity> findByLogin(String login);
    List<UserEntity> findByEmail(String email);
    List<UserEntity> findByRecoveryEmail(String recoveryEmail);
}
