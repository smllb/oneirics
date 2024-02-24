package com.lucid.oneiric.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lucid.oneiric.dto.DreamDTO;
import com.lucid.oneiric.dto.UserNewDreamDTO;
import com.lucid.oneiric.services.DreamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@RestController
public class DreamsController {

    public DreamService dreamService;

    public DreamsController(DreamService dreamService) {
        this.dreamService = dreamService;
    }

    @PostMapping("/newdream")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> newDream(@RequestBody UserNewDreamDTO userNewDreamDTO) {
        System.out.println(userNewDreamDTO.toString());
        return dreamService.saveNewDream((userNewDreamDTO));
    }

    @GetMapping("/dream/{id}")
    public ResponseEntity<DreamDTO> getDreamByDreamId(@PathVariable String id) {
        System.out.println(id);
        DreamDTO dream = dreamService.getDreamById(id);
        return ResponseEntity.ok(dream);

    }

    @GetMapping("/dreams/visibility/{visibility}")
    public ResponseEntity<List<DreamDTO>> getAllDreamsByVisibilityId(@PathVariable String visibility) throws JsonProcessingException {
        System.out.println("dream by visibility");
        List<DreamDTO> dreams = dreamService.getDreamsByVisibilityId(visibility);
        return ResponseEntity.ok(dreams);

    }
//
    @GetMapping("dreams/session/")
    public ResponseEntity<List<DreamDTO>> getAllDreamsByUser() {
//        System.out.println("dreams by user");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<DreamDTO> userDreams = dreamService.getDreamsByUser(username);
        return ResponseEntity.ok(userDreams);

    }

    @GetMapping("dreams/user/{username}")
    public ResponseEntity<List<DreamDTO>> getAllDreamsByUser(@PathVariable String username) {
//        System.out.println("dreams by user");
        // ADD USER CONTROL HERE!!!
        List<DreamDTO> userDreams = dreamService.getDreamsByUser(username);
        return ResponseEntity.ok(userDreams);

    }

}
