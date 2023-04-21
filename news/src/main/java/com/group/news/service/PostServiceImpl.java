package com.group.news.service;

import com.group.news.dao.PostDAO;
import com.group.news.dao.PostRepository;
import com.group.news.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;

    private PostDAO postDAO;

    @Autowired
    public PostServiceImpl (PostRepository postRepository, PostDAO postDAO) {
        this.postRepository = postRepository;
        this.postDAO = postDAO;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostsFromDb(int pageNumber) {
        return postDAO.getPostsFromDb(pageNumber);
    }

    @Override
    public Post findById(int Id) {
        Optional<Post> result = postRepository.findById(Id);

        Post thePost = null;

        if (result.isPresent()) {
            thePost = result.get();
        } else {
            throw new RuntimeException("Did not find employee");
        }

        return thePost;
    }

    @Override
    public Post save(Post thePost) {
        return postRepository.save(thePost);
    }

}
