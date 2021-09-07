package com.codegym.service;

import com.codegym.model.Blog;
import com.codegym.model.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    Iterable<Comment> findAll();

    public List<Comment> findCommentByIdBlog(long id);


    Comment save(Comment comment);

    void remove(long id);
}
