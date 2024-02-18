package com.lucid.oneiric.controller;

import com.lucid.oneiric.dto.DreamDTO;
import com.lucid.oneiric.dto.UserNewDreamDTO;
import com.lucid.oneiric.entities.DreamEntity;
import com.lucid.oneiric.repository.DreamRepository;
import com.lucid.oneiric.services.DreamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<DreamDTO> newDream(@PathVariable String id) {
        System.out.println(id);
        DreamDTO dream = dreamService.getDreamById(id);
        return ResponseEntity.ok(dream);

    }
}
