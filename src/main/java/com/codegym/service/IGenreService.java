package com.codegym.service;

import com.codegym.model.Genre;

import java.util.Optional;

public interface IGenreService {
    Iterable<Genre> findAll();

    Optional<Genre> findById(long id);

}
