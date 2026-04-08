package com.klu.ArtifyBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.ArtifyBackend.model.Artwork;
import com.klu.ArtifyBackend.service.ArtworkService;

@RestController
@RequestMapping("/artworks")
@CrossOrigin
public class ArtworkController {

    @Autowired
    private ArtworkService service;

    @PostMapping("/upload")
    public Artwork upload(@RequestBody Artwork art) {
        return service.upload(art);
    }

    @GetMapping
    public List<Artwork> getAll() {
        return service.getAll();
    }

    @GetMapping("/artist/{id}")
    public List<Artwork> getByArtist(@PathVariable Long id) {
        return service.getByArtist(id);
    }

    @DeleteMapping("/{id}")
    public String deleteArtwork(@PathVariable Long id) {
        return service.deleteArtwork(id);
    }
}