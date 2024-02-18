package com.lucid.oneiric.services;

import com.lucid.oneiric.dto.DreamDTO;
import com.lucid.oneiric.dto.UserNewDreamDTO;
import com.lucid.oneiric.entities.DreamEntity;
import com.lucid.oneiric.mappers.DreamMapper;
import com.lucid.oneiric.repository.DreamRepository;
import com.lucid.oneiric.repository.UsersRepository;
import com.lucid.oneiric.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DreamService {

    private final DreamRepository dreamRepository;
    private final UsersRepository usersRepository;
    @Autowired
    public DreamService(DreamRepository dreamRepository, UsersRepository usersRepository) {
        this.dreamRepository = dreamRepository;
        this.usersRepository = usersRepository;
    }

    public ResponseEntity<String> saveNewDream(UserNewDreamDTO userNewDreamDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        String authorId = usersRepository.findByLogin(userPrincipal.getUsername()).get(0).getId();
        Integer dreamKind = (userNewDreamDTO.getDreamKind().equals("lucid") ? 0 : 1);

        DreamEntity newDream = new DreamEntity(authorId, userNewDreamDTO.getDreamTitle(), userNewDreamDTO.getDreamContent(), dreamKind, 1, 2);
        try {
            dreamRepository.save(newDream);
            return ResponseEntity.status(HttpStatus.OK).body("New dream registered on db");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad bad bad");
        }
    }

    public DreamDTO getDreamById(String dreamId) {
        return DreamMapper.toDto(dreamRepository.findById(dreamId).get());
    }

}
