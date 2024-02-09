package com.lucid.oneiric.services;

import com.lucid.oneiric.dto.UserRegistrationDTO;
import com.lucid.oneiric.entities.UserEntity;
import com.lucid.oneiric.enums.AccountType;
import com.lucid.oneiric.repository.UsersRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RegisterService {

    private final UsersRepository usersRepository;
    private final SecurePasswordService securePasswordService;

    public RegisterService(UsersRepository usersRepository, SecurePasswordService securePasswordService) {
        this.usersRepository = usersRepository;
        this.securePasswordService = securePasswordService;
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

        HashMap<String, String> userPasswordCredentials = securePasswordService.encodeRawPassword(userRegistrationDTO.getPassword());
        AccountType accountType = AccountType.USER;
        UserEntity user = new UserEntity(userRegistrationDTO.getLogin(), userPasswordCredentials.get("salt"),userPasswordCredentials.get("hash"), userRegistrationDTO.getEmail(), userRegistrationDTO.getRecoveryEmail(), accountType.getValue());
        usersRepository.save(user);
        result = "User registered successfully.";
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
