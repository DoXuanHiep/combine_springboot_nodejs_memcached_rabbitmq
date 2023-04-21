package com.group.news.entity;

import jakarta.persistence.*;

@Entity
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "amount_like", nullable = false, columnDefinition = "integer default 0")
    private int amountLike;

    @ManyToOne()
    @JoinColumn(name = "title_id", nullable = false)
    private Title title;

    public Post() {};

    public Post(String content, Title title) {
        this.content = content;
        this.title = title;
    }

    public Post(int id, String content, int amountLike, Title title) {
        this.id = id;
        this.content = content;
        this.amountLike = amountLike;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public Post setId(int id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    public int getAmountLike() {
        return amountLike;
    }

    public Post setAmountLike(int amountLike) {
        this.amountLike = amountLike;
        return this;
    }

    public Title getTitle() {
        return title;
    }

    public Post setTitle(Title title) {
        this.title = title;
        return this;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", amountLike=" + amountLike +
                ", title=" + title +
                '}';
    }
}
