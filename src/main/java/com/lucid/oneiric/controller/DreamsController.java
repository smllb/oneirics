package com.lucid.oneiric.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lucid.oneiric.dto.DreamDTO;
import com.lucid.oneiric.dto.UserNewDreamDTO;
import com.lucid.oneiric.entities.DreamEntity;
import com.lucid.oneiric.entities.UserEntity;
import com.lucid.oneiric.services.DreamService;
import com.lucid.oneiric.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DreamsController {

    public DreamService dreamService;
    private final UsersService usersService;

    public DreamsController(DreamService dreamService, UsersService usersService) {
        this.dreamService = dreamService;
        this.usersService = usersService;
    }

    @PostMapping("/dream/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> newDream(@RequestBody UserNewDreamDTO userNewDreamDTO) {
        System.out.println(userNewDreamDTO.toString());
        return dreamService.saveNewDream((userNewDreamDTO));
    }

    @GetMapping("/dream/search/{id}")
    public ResponseEntity<DreamDTO> getDreamByDreamId(@PathVariable String id) {
        System.out.println(id);
        DreamEntity dream = dreamService.getDreamEntityById(id);
        if (dream != null) {
            if (dream.getVisibilityEntity().getId().equals(1)) {
                if (SecurityContextHolder.getContext().getAuthentication().getName().equals(dream.getUserEntity().getLogin())) {
                    return ResponseEntity.ok(dreamService.toDTO(dream));
                }
                return ResponseEntity.status(403).build();
            } else {
                return ResponseEntity.ok(dreamService.toDTO(dream));
            }
        }

        return ResponseEntity.status(400).build();
    }

    @GetMapping("/dreams/search/public")
    public ResponseEntity<List<DreamDTO>> getAllPublicDreams() {
        return ResponseEntity.ok(dreamService.getDreamsDTOByVisibilityId(2));

    }


    // only for adm
    @GetMapping("/dreams/search/visibility/{visibilityId}")
    public ResponseEntity<List<DreamDTO>> getAllDreamsByVisibilityId(@PathVariable Integer visibilityId) throws JsonProcessingException {
        return ResponseEntity.ok(dreamService.getDreamsDTOByVisibilityId(visibilityId));

    }

    @GetMapping("dreams/search/session/")
    public ResponseEntity<List<DreamDTO>> getAllDreamsByUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<DreamDTO> userDreams = dreamService.getDreamsByUser(username);
        return ResponseEntity.ok(userDreams);

    }

    @GetMapping("dreams/search/user/{username}")
    public ResponseEntity<List<DreamDTO>> getAllDreamsByUser(@PathVariable String username) {
        List<DreamDTO> userDreams = new ArrayList<>();

        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(username)) {
            userDreams = dreamService.getDreamsByUser(username);
        } else {
            userDreams = dreamService.getAllDreamsDTOByUserEntityLoginAndVisibilityEntityId(username, 2);
        }
        return ResponseEntity.ok(userDreams);

    }

    @DeleteMapping("dreams/remove/{dreamId}")
    public ResponseEntity<String> removeDreamById(@PathVariable String dreamId) {
        UserEntity user = usersService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        DreamEntity dreamToBeRemoved = dreamService.getDreamEntityById(dreamId);
        if (dreamToBeRemoved == null) {
            return ResponseEntity.status(400).body("No dream with that id.");

        } else {
            if (user.getLogin().equals(dreamToBeRemoved.getUserEntity().getLogin()) || user.isAdmin()) {
                if (dreamService.removeDreamById(dreamId)) {
                    return ResponseEntity.ok("Removed dream with id " + dreamId);

                }
                return ResponseEntity.status(500).body("Couldn't delete dream. ");

            }
        }
        return ResponseEntity.status(403).body("Insufficient rights.");
    }

}
