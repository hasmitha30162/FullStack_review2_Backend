package com.klu.ArtifyBackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.ArtifyBackend.model.Artwork;
import com.klu.ArtifyBackend.repository.ArtworkRepository;

@Service
public class ArtworkService {

    @Autowired
    private ArtworkRepository repo;

    public Artwork upload(Artwork art) {
        return repo.save(art);
    }

    public List<Artwork> getAll() {
        return repo.findAll();
    }

    public List<Artwork> getByArtist(Long id) {
        return repo.findByArtistId(id);
    }

    public String deleteArtwork(Long id) {
        if (!repo.existsById(id)) {
            return "Artwork not found";
        }

        repo.deleteById(id);
        return "Artwork deleted successfully";
    }
}