package com.lucid.oneiric.controller;

import com.lucid.oneiric.dto.DreamDTO;
import com.lucid.oneiric.dto.UserNewDreamDTO;
import com.lucid.oneiric.entities.DreamEntity;
import com.lucid.oneiric.entities.UserEntity;
import com.lucid.oneiric.security.UserPrincipal;
import com.lucid.oneiric.services.DreamService;
import com.lucid.oneiric.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dreams")
public class DreamsController {

    public DreamService dreamService;
    private final UsersService usersService;

    public DreamsController(DreamService dreamService, UsersService usersService) {
        this.dreamService = dreamService;
        this.usersService = usersService;
    }

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> newDream(@RequestBody UserNewDreamDTO userNewDreamDTO) {
        System.out.println(userNewDreamDTO.toString());
        return dreamService.saveNewDream((userNewDreamDTO));


    }

    @GetMapping("search/daily")
    public ResponseEntity<List<DreamDTO>> getDailyDreams() {
        return ResponseEntity.ok(dreamService.getAllAccessibleDreamsFromToday());

    }

    @GetMapping(value = "search", params = "dream")
    public ResponseEntity<DreamDTO> getDreamByDreamId(@RequestParam String dream) {
        DreamEntity dreamEntity = dreamService.getDreamEntityById(dream);

        if (dream == null) {
           return ResponseEntity.status(404).build();
        }

        UserPrincipal client = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAuthorOrAdmin = client.getUsername().equals(dreamEntity.getUserEntity().getLogin()) || client.isAdmin();
        boolean isDreamPrivate = dreamEntity.getVisibilityEntity().getId().equals(1);

        if (isDreamPrivate && !isAuthorOrAdmin) {
            return ResponseEntity.status(403).build();

        }

        return ResponseEntity.ok(dreamService.toDTO(dreamEntity));

    }

    @GetMapping(value = "search/public", params = {"page", "items" })
    public ResponseEntity<List<DreamDTO>> getAllPublicDreams(Integer page, Integer items) {
        return ResponseEntity.ok(dreamService.getDreamsDTOByVisibilityId(2, page, items));

    } // mess here


    // only for adm
    @GetMapping(value = "search", params = {"visibility", "page", "items"})
    public ResponseEntity<List<DreamDTO>> getAllDreamsByVisibilityId(@RequestParam Integer visibility, @RequestParam Integer page, @RequestParam Integer items) {
        return ResponseEntity.ok(dreamService.getDreamsDTOByVisibilityId(visibility, page, items));

    }

    @GetMapping("dreams/search/session")
    public ResponseEntity<List<DreamDTO>> getAllDreamsByUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<DreamDTO> userDreams = dreamService.getDreamsByUser(username);
        return ResponseEntity.ok(userDreams);

    }

    @GetMapping("search/user/{username}")
    public ResponseEntity<List<DreamDTO>> getAllDreamsByUser(@PathVariable String username) {
        List<DreamDTO> userDreams = new ArrayList<>();

        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(username)) {
            userDreams = dreamService.getDreamsByUser(username);
        } else {
            userDreams = dreamService.getAllDreamsDTOByUserEntityLoginAndVisibilityEntityId(username, 2);
        }
        return ResponseEntity.ok(userDreams);

    }

    @DeleteMapping("delete/id/{dreamId}")
    public ResponseEntity<String> deleteDreamById(@PathVariable String dreamId) {
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
