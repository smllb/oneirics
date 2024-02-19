package com.lucid.oneiric.services;

import com.lucid.oneiric.dto.DreamDTO;
import com.lucid.oneiric.dto.UserNewDreamDTO;
import com.lucid.oneiric.entities.*;
import com.lucid.oneiric.repository.*;
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
    private final UsersRepository userRepository;
    private final DreamTypeRepository dreamTypeRepository;
    private final DreamCategoryRepository dreamCategoryRepository;
    private final VisibilityRepository visibilityRepository;

    public DreamService(DreamRepository dreamRepository, UsersRepository userRepository, DreamTypeRepository dreamTypeRepository, DreamCategoryRepository dreamCategoryRepository, VisibilityRepository visibilityRepository) {
        this.dreamRepository = dreamRepository;
        this.userRepository = userRepository;
        this.dreamTypeRepository = dreamTypeRepository;
        this.dreamCategoryRepository = dreamCategoryRepository;
        this.visibilityRepository = visibilityRepository;
    }


    public ResponseEntity<String> saveNewDream(UserNewDreamDTO userNewDreamDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        UserEntity author = userRepository.findById(userPrincipal.getId()).orElseThrow(() -> new RuntimeException("no author"));
        DreamTypeEntity dreamKind = dreamTypeRepository.findById(userNewDreamDTO.getDreamKind()).orElseThrow(() -> new RuntimeException("no dream kind"));
        DreamCategoryEntity dreamCategory = dreamCategoryRepository.findById(userNewDreamDTO.getDreamCategory()).orElseThrow(() -> new RuntimeException("no dream category"));
        VisibilityEntity visibility = visibilityRepository.findById(userNewDreamDTO.getVisibilityId()).orElseThrow(() -> new RuntimeException("no visibility"));


        DreamEntity newDream = new DreamEntity(author, dreamKind, dreamCategory, visibility, userNewDreamDTO.getDreamTitle(), userNewDreamDTO.getDreamContent());
        try {
            dreamRepository.save(newDream);
            return ResponseEntity.status(HttpStatus.OK).body(newDream.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad bad bad");
        }
    }

    public DreamDTO getDreamById(String dreamId) {
        return dreamRepository.fillDreamDTOById(dreamId);

    }

}
