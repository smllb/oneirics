package com.lucid.oneiric.services;

import com.lucid.oneiric.dto.UserRegistrationDTO;
import com.lucid.oneiric.entities.RoleEntity;
import com.lucid.oneiric.entities.UserEntity;
import com.lucid.oneiric.enums.AccountType;
import com.lucid.oneiric.repository.RoleRepository;
import com.lucid.oneiric.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RegisterService {

    private final UsersRepository usersRepository;

    private final RoleRepository roleRepository;
    private final SecurePasswordService securePasswordService;

    public RegisterService(UsersRepository usersRepository, SecurePasswordService securePasswordService, RoleRepository roleRepository) {
        this.usersRepository = usersRepository;
        this.securePasswordService = securePasswordService;
        this.roleRepository = roleRepository;

    }

    public ResponseEntity<String> register(UserRegistrationDTO userRegistrationDTO){
        String result;

        if (!usersRepository.findByLogin(userRegistrationDTO.getLogin()).isEmpty()) {
            result = "Username already exists.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        } else if (!usersRepository.findByEmail(userRegistrationDTO.getEmail()).isEmpty()) {
            result = "Email already exists.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        } else if (userRegistrationDTO.getRecoveryEmail() != null && !usersRepository.findByRecoveryEmail(userRegistrationDTO.getRecoveryEmail()).isEmpty()) {
            result = "Recovery email already in use.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        String argon2HashedPassword = securePasswordService.encodeRawPassword(userRegistrationDTO.getPassword());
        RoleEntity defaultUserRole = roleRepository.findByName("ROLE_USER");
        RoleEntity mockRole = roleRepository.findByName("ROLE_ADMIN");
        UserEntity user = new UserEntity(userRegistrationDTO.getLogin(), argon2HashedPassword, userRegistrationDTO.getEmail(), userRegistrationDTO.getRecoveryEmail());
        user.addRole(defaultUserRole);
        user.addRole(mockRole);
        usersRepository.save(user);
        result = "User registered successfully.";
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
