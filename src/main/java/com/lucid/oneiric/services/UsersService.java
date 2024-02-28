package com.lucid.oneiric.services;

import com.lucid.oneiric.dto.UserPublicViewDTO;
import com.lucid.oneiric.dto.UserRegistrationDTO;
import com.lucid.oneiric.entities.UserEntity;
import com.lucid.oneiric.exceptions.MissingEmailDataException;
import com.lucid.oneiric.exceptions.MissingLoginDataException;
import com.lucid.oneiric.exceptions.MissingMultipleRequiredFieldsException;
import com.lucid.oneiric.exceptions.MissingPasswordDataException;
import com.lucid.oneiric.mappers.PublicUserMapper;
import com.lucid.oneiric.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    private final RegisterService registerService;
    private final UsersRepository usersRepository;
    private final SecurePasswordService securePasswordService;

    private final PublicUserMapper publicUserMapper;

    public UsersService(RegisterService registerService, UsersRepository usersRepository, SecurePasswordService securePasswordService, PublicUserMapper publicUserMapper) {
        this.registerService = registerService;
        this.usersRepository = usersRepository;
        this.securePasswordService = securePasswordService;
        this.publicUserMapper = publicUserMapper;
    }

    public ResponseEntity<String> registerNewUser(UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            if (bindingResult.getFieldErrors().size() > 2) {
                throw new MissingMultipleRequiredFieldsException();
            }
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                String fieldName = fieldError.getField();
                switch (fieldName) {
                    case "login":
                        throw new MissingLoginDataException();
                    case "password":
                        throw new MissingPasswordDataException();
                    case "email":
                        throw new MissingEmailDataException();
                }
            }
            return ResponseEntity.badRequest().body("Unknown invalid request.");
        }

        return registerService.register(userRegistrationDTO);

    }

    @Transactional
    public Boolean updateUserPassword(String userLogin, String newPassword) {
        try {
            UserEntity user = usersRepository.findByLogin(userLogin).get(0);
            user.setPassword(securePasswordService.encodeRawPassword(newPassword));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public UserEntity getUserByLogin(String login) {
        return usersRepository.findByLogin(login).get(0);

    }
    public List<UserPublicViewDTO> getAllUserPublicViews() {
        try {
            List<UserEntity> users = usersRepository.findAll();
            List<UserPublicViewDTO> usersDTO = new ArrayList<>();
            for (UserEntity user : users) {
                UserPublicViewDTO userDTO = new UserPublicViewDTO();
                usersDTO.add(publicUserMapper.mapUserDataToPublicViewDTO(user));

            }
            return usersDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public UserPublicViewDTO getUserPublicView(String login) {
        try {
            UserEntity user = usersRepository.findByLogin(login).get(0);
            return publicUserMapper.mapUserDataToPublicViewDTO(user);

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public Boolean removeUserByLogin(String login) {
        try {
            UserEntity user = usersRepository.findByLogin(login).get(0);
            usersRepository.delete(user);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @Transactional
    public Boolean setUserStatusSuspendedByLogin(String login) {
        try {
            UserEntity user = usersRepository.findByLogin(login).get(0);
            user.setStatus("SUSPENDED");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }

    }
        public Boolean setUserStatusActiveByLogin(String login) {
            try {
                UserEntity user = usersRepository.findByLogin(login).get(0);
                user.setStatus("ACTIVE");
                return true;

            } catch (Exception e) {
                e.printStackTrace();
                return false;

            }

    }

}
