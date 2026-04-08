package com.klu.ArtifyBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.klu.ArtifyBackend.model.User;
import com.klu.ArtifyBackend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public List<User> getUsersOnly() {
        return repo.findByRole("USER");
    }

    public List<User> getArtistsOnly() {
        return repo.findByRole("ARTIST");
    }
}
