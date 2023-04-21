package com.group.news.service.memcached;

public class MemcachedTitle {
    private Integer titleId;

    private String genre;

    public  MemcachedTitle() {};

    public MemcachedTitle(Integer titleId, String genre) {
        this.titleId = titleId;
        this.genre = genre;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public MemcachedTitle setTitleId(Integer titleId) {
        this.titleId = titleId;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public MemcachedTitle setGenre(String genre) {
        this.genre = genre;
        return this;
    }
}
