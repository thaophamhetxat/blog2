package com.codegym.service;

import com.codegym.model.Comment;
import com.codegym.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Autowired
    ICommentRepo iCommentRepo;

    @Override
    public Iterable<Comment> findAll() {
        return iCommentRepo.findAll();
    }

    @Override
    public List<Comment> findCommentByIdBlog(long id) {
        return iCommentRepo.findCommentByIdBlog(id);
    }



    @Override
    public Comment save(Comment comment) {
        return iCommentRepo.save(comment);
    }

    @Override
    public void remove(long id) {
        iCommentRepo.deleteById(id);
    }
}
