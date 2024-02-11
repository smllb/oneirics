package com.lucid.oneiric.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    @RequestMapping("/hello")
    public String helloAdmin() {
        return "hello adm";
    }
}
