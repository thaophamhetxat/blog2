package com.codegym.repository;

import com.codegym.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface IGenreRepo extends CrudRepository<Genre,Long> {
}
