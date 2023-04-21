package com.group.news.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="title")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="genre", nullable = false)
    private String genre;

    @OneToMany(mappedBy = "title")
    @JsonIgnore
    private Set<Post> posts;

    public Title() {};

    public Title(String genre) {
        this.genre = genre;
    }

    public Title(int id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public Title(int id, String genre, Set<Post> posts) {
        this.id = id;
        this.genre = genre;
        this.posts = posts;
    }

    public int getId() {
        return id;
    }

    public Title setId(int id) {
        this.id = id;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public Title setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public Title setPosts(Set<Post> posts) {
        this.posts = posts;
        return this;
    }

    @Override
    public String toString() {
        return "Title{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                ", posts=" + posts +
                '}';
    }
}
