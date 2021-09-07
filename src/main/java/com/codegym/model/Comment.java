package com.codegym.model;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String comments;


    @ManyToOne
    @JoinColumn(name = "comment_blog_id")
    private Blog blogs;

    public Comment() {
    }

    public Comment(long id, String comments, Blog blogs) {
        this.id = id;
        this.comments = comments;
        this.blogs = blogs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Blog getBlogs() {
        return blogs;
    }

    public void setBlogs(Blog blogs) {
        this.blogs = blogs;
    }
}
