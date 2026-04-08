package com.klu.ArtifyBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.ArtifyBackend.dto.LoginRequest;
import com.klu.ArtifyBackend.dto.SignupRequest;
import com.klu.ArtifyBackend.model.User;
import com.klu.ArtifyBackend.service.AuthService;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest req) {
        return service.signup(req);
    }

    @PostMapping("/verify")
    public String verify(@RequestParam String email,
                         @RequestParam String role,
                         @RequestParam String otp) {
        return service.verify(email, role, otp);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest req) {
        return service.login(req);
    }
}