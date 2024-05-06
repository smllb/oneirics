package com.lucid.oneiric.services;

import org.springframework.stereotype.Service;

@Service
public class OneiricsService {
    private final DreamService dreamService;
    private final UsersService usersService;


    public OneiricsService(DreamService dreamService, UsersService usersService) {
        this.dreamService = dreamService;
        this.usersService = usersService;
    }


}
