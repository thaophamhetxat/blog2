package com.codegym.model;

import javax.persistence.*;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String imageSubject;
    String musicSubject;
    String note;

    @ManyToOne
    private Blog blog;

    public Subject() {
    }

    public Subject(int id, String imageSubject, String musicSubject, String note, Blog blog) {
        this.id = id;
        this.imageSubject = imageSubject;
        this.musicSubject = musicSubject;
        this.note = note;
        this.blog = blog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageSubject() {
        return imageSubject;
    }

    public void setImageSubject(String imageSubject) {
        this.imageSubject = imageSubject;
    }

    public String getMusicSubject() {
        return musicSubject;
    }

    public void setMusicSubject(String musicSubject) {
        this.musicSubject = musicSubject;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
