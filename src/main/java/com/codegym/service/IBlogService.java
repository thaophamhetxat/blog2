package com.codegym.service;

import com.codegym.model.Blog;

import java.util.Optional;

public interface IBlogService {
    Iterable<Blog> findAll();

    Optional<Blog> findById(long id);

    Blog save(Blog blog);

    void remove(long id);


    void likes(long id);
    void dislikes(long id);
    void show(long id);
}
