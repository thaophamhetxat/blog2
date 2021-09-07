package com.codegym.service;

import com.codegym.model.Blog;
import com.codegym.repository.IBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BlogService implements IBlogService {
    @Autowired
    IBlogRepo iBlogRepo;

    @Override
    public Iterable<Blog> findAll() {
        return iBlogRepo.findAll();
    }

    @Override
    public Optional<Blog> findById(long id) {
        return iBlogRepo.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        return iBlogRepo.save(blog);
    }

    @Override
    public void remove(long id) {
        iBlogRepo.deleteById(id);
    }




    @Override
    public void likes(long id) {
        Optional<Blog> like = iBlogRepo.findById(id);
        like.get().setLikes(like.get().getLikes() + 1);
        iBlogRepo.save(like.get());
    }

    @Override
    public void dislikes(long id) {
        Optional<Blog> dislike = iBlogRepo.findById(id);
        dislike.get().setDislike(dislike.get().getDislike() + 1);
        iBlogRepo.save(dislike.get());
    }

    @Override
    public void show(long id) {
        Optional<Blog> show = iBlogRepo.findById(id);
        show.get().setViews(show.get().getViews() + 1);
        iBlogRepo.save(show.get());
    }


}
