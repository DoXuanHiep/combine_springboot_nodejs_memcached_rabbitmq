package com.group.news.dao;

import com.group.news.entity.Post;

import java.util.List;

public interface PostDAO {
    List<Post> getPostsFromDb(int pageNumber);
}
