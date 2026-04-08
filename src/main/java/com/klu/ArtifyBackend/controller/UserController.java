package com.klu.ArtifyBackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.klu.ArtifyBackend.model.User;
import com.klu.ArtifyBackend.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/only-users")
    public List<User> getUsersOnly() {
        return service.getUsersOnly();
    }

    @GetMapping("/artists")
    public List<User> getArtistsOnly() {
        return service.getArtistsOnly();
    }
}