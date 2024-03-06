package com.lucid.oneiric.services;

import com.lucid.oneiric.dto.DreamDTO;
import com.lucid.oneiric.dto.UserNewDreamDTO;
import com.lucid.oneiric.entities.*;
import com.lucid.oneiric.mappers.DreamMapper;
import com.lucid.oneiric.repository.*;
import com.lucid.oneiric.security.UserPrincipal;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DreamService {

    private final DreamRepository dreamRepository;
    private final DreamMapper dreamMapper;
    private final UsersRepository userRepository;
    private final DreamTypeRepository dreamTypeRepository;
    private final DreamCategoryRepository dreamCategoryRepository;
    private final VisibilityRepository visibilityRepository;

    public DreamService(DreamRepository dreamRepository, DreamMapper dreamMapper, UsersRepository userRepository, DreamTypeRepository dreamTypeRepository, DreamCategoryRepository dreamCategoryRepository, VisibilityRepository visibilityRepository) {
        this.dreamRepository = dreamRepository;
        this.dreamMapper = new DreamMapper();
        this.userRepository = userRepository;
        this.dreamTypeRepository = dreamTypeRepository;
        this.dreamCategoryRepository = dreamCategoryRepository;
        this.visibilityRepository = visibilityRepository;
    }


    public List<DreamDTO> getAllAccessibleDreamsFromToday() {
        List<DreamEntity> dreams = dreamRepository.findAllByCreationDateBetween(LocalDateTime.of(LocalDate.now(), LocalTime.MIN), LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
        List<DreamDTO> dreamsDTO = new ArrayList<>();
        String clientName = SecurityContextHolder.getContext().getAuthentication().getName();

        for (DreamEntity dream : dreams) {
            System.out.println(clientName + dream.getUserEntity().getLogin());
            if (dream.getVisibilityEntity().getId().equals(1) && dream.getUserEntity().getLogin().equals(clientName)) {

                dreamsDTO.add(dreamMapper.dreamEntityToDto(Optional.ofNullable(dream)));

            }
            if (!dream.getVisibilityEntity().getId().equals(1)) {
                dreamsDTO.add(dreamMapper.dreamEntityToDto(Optional.ofNullable(dream)));
            }

        }
        return dreamsDTO;

    }

    @Transactional
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
            if (dreamKind.getId().equals(1)) {
                author.setLucidDreamCount(author.getLucidDreamCount()+1);
            } else {
                author.setRegularDreamCount(author.getRegularDreamCount()+1);
            }
            return ResponseEntity.status(HttpStatus.OK).body(newDream.getId());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad bad bad");

        }
    }

    public DreamDTO getDreamDTOById(String dreamId) {
        try {
            @NonNull Optional<DreamEntity> dream = dreamRepository.findById(dreamId);

            return dreamMapper.dreamEntityToDto(dream);

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }

    public List<DreamDTO> getDreamsDTOByVisibilityId(Integer visibilityId) {
        try {
            List<DreamDTO> dreamsDTO = new ArrayList<>();
            Optional<List<DreamEntity>> optionalDreams = Optional.ofNullable(dreamRepository.findAllByVisibilityEntityId(visibilityId));

            if (optionalDreams.isPresent()) {
                for (DreamEntity dream : optionalDreams.get()) {
                    dreamsDTO.add(dreamMapper.dreamEntityToDto(Optional.ofNullable(dream)));
                }
                return dreamsDTO;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return Collections.emptyList();

    }

    public List<DreamDTO> getAllDreamsDTOByUserEntityLoginAndVisibilityEntityId(String userEntity_login, Integer visibilityEntity_id) {
        List<DreamDTO> dreamsDTO = new ArrayList<>();
        Optional<List<DreamEntity>> optionalDreams = dreamRepository.findAllByUserEntityLoginAndVisibilityEntityId(userEntity_login, visibilityEntity_id);
        if (optionalDreams.isPresent()) {
            for (DreamEntity dream : optionalDreams.get()) {
                dreamsDTO.add(dreamMapper.dreamEntityToDto(Optional.ofNullable(dream)));
            }
            return dreamsDTO;
        }
        return null;

    }
    public List<DreamEntity> getAllDreamEntitiesByVisibilityId(Integer visibilityId) {
        return dreamRepository.findAllByVisibilityEntityId(visibilityId);

    }
    public List<DreamEntity> getAllByUserEntityLogin(String entityLogin) {
        return dreamRepository.findAllByUserEntityLogin(entityLogin);

    }

    public List<DreamDTO> getDreamsByUser(String username) {
        try {
            List<DreamDTO> dreamsDTO = new ArrayList<>();
            List<DreamEntity> dreams = dreamRepository.findAllByUserEntityLogin(username);
            for (DreamEntity dream : dreams) {
                dreamsDTO.add(dreamMapper.dreamEntityToDto(Optional.ofNullable(dream)));
            }
            return dreamsDTO;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return Collections.emptyList();

    }

    @Transactional
    public Boolean removeDreamById(String id) {
        Optional<DreamEntity> optionalDream = dreamRepository.findById(id);
        if (optionalDream.isPresent()) {
            dreamRepository.delete(optionalDream.get());
            return true;
        }
        return false;
    }

    public DreamEntity getDreamEntityById(String id) {
        Optional<DreamEntity> optionalDream = dreamRepository.findById(id);
        if (optionalDream.isPresent()) {
            return optionalDream.get();
        }
        System.out.println("no dream with that id");
        return null;
    }

    public DreamDTO toDTO (DreamEntity dream) {
        return dreamMapper.dreamEntityToDto(Optional.ofNullable(dream));

    }

}
