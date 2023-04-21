package com.group.news.service.memcached;

import com.group.news.entity.Post;

import java.util.List;

public interface MemcachedService {
    List<Post> getPostsFromMemcached(int pageNumber);
}
