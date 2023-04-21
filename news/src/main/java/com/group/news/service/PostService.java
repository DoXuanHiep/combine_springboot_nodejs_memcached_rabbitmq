package com.group.news.service;

import com.group.news.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> findAll();

    List<Post> getPostsFromDb(int pageNumber);

    Post findById(int Id);

    Post save(Post thePost);
}
