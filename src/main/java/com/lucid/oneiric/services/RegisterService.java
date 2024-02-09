package com.lucid.oneiric.services;

import com.lucid.oneiric.dto.UserRegistrationDTO;
import com.lucid.oneiric.entities.UserEntity;
import com.lucid.oneiric.enums.AccountType;
import com.lucid.oneiric.repository.UsersRepository;
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

    public boolean register(UserRegistrationDTO userRegistrationDTO){
        System.out.println(userRegistrationDTO.getPassword());
        if (!usersRepository.findByLogin(userRegistrationDTO.getLogin()).isEmpty()) {
            System.out.println("Username already exists.");
            return false;
        } else if (!usersRepository.findByEmail(userRegistrationDTO.getEmail()).isEmpty()) {
            System.out.println("Email already exists.");
            return false;
        } else if (userRegistrationDTO.getRecoveryEmail() != null && !usersRepository.findByRecoveryEmail(userRegistrationDTO.getRecoveryEmail()).isEmpty()) {
            System.out.println("Recovery email already in use.");
            return false;
        }

        HashMap<String, String> userPasswordCredentials = securePasswordService.encodeRawPassword(userRegistrationDTO.getPassword());
        AccountType accountType = AccountType.USER;
        UserEntity user = new UserEntity(userRegistrationDTO.getLogin(), userPasswordCredentials.get("salt"),userPasswordCredentials.get("hash"), userRegistrationDTO.getEmail(), userRegistrationDTO.getRecoveryEmail(), accountType.getValue());
        System.out.println(user.getId());
        System.out.println(user.getId().getClass());
        System.out.println(accountType.getValue());
        usersRepository.save(user);
        return true;
    }
}
