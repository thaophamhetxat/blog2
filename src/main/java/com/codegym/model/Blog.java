package com.codegym.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;


@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String title;
    String content;
    Date date;
    int views;
    int likes;
    int dislike;





    @ManyToOne
    private Genre genre;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Comment> comments;

    public Blog() {
    }

    public Blog(long id, String title, String content,
                Date date, int views, int likes, int dislike, Genre genre, Set<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.views = views;
        this.likes = likes;
        this.dislike = dislike;
        this.genre = genre;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
