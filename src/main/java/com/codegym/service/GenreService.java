package com.codegym.service;

import com.codegym.model.Genre;
import com.codegym.repository.IGenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreService implements IGenreService {
    @Autowired
    IGenreRepo iGenreRepo;

    @Override
    public Iterable<Genre> findAll() {
        return iGenreRepo.findAll();
    }

    @Override
    public Optional<Genre> findById(long id) {
        return iGenreRepo.findById(id);
    }
}
