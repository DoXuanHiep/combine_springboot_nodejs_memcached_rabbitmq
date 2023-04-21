package com.group.news.rest;

import com.group.news.entity.Post;
import com.group.news.entity.Title;
import com.group.news.service.PostService;
import com.group.news.service.TitleService;
import com.group.news.service.memcached.MemcachedService;
import com.group.news.service.producerRabitmq.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostRestController {
    private PostService postService;
    private TitleService titleService;
    private ProducerService producerService;
    private MemcachedService memcachedService;

    @Autowired
    public PostRestController(PostService postService, TitleService titleService, ProducerService producerService,
                              MemcachedService memcachedService) {
        this.postService = postService;
        this.titleService = titleService;
        this.producerService = producerService;
        this.memcachedService = memcachedService;
    }

    @GetMapping("/posts")
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping("/posts/{pageNumber}")
    public List<Post> getPosts(@PathVariable int pageNumber) {
        if (pageNumber == 1 || pageNumber == 2) {
            return memcachedService.getPostsFromMemcached(pageNumber);
        }
        return postService.getPostsFromDb(pageNumber);
    }

    @GetMapping("/post/{postId}")
    public Post findById(@PathVariable int postId) {
        Post thePost = postService.findById(postId);

        if (thePost == null) {
            throw new RuntimeException("Post is not found");
        }

        return thePost;
    }

    @PostMapping("/post/add-post")
    public String addPost(@RequestBody String data) {
//        String content = data.get("content").toString();
//
//        int titleId = Integer.parseInt(data.get("titleId").toString());
//        Title theTitle = titleService.findById(titleId);
//
//        if (theTitle == null) {
//            throw new RuntimeException("title's not found");
//        }
//        Post thePost = new Post(content, theTitle);

//        Post dbPost = postService.save(thePost);
        String action = "create_post";
        producerService.sendMessage(data, action);

        return "Create Successfully";
    }

    @PutMapping("/post/update-post")
    public String update(@RequestBody String data) {

//        int postId = Integer.parseInt(data.get("postId").toString());
//        Post thePost = postService.findById(postId);
//
//        if (thePost == null) {
//            throw new RuntimeException("post's not found");
//        }"
//
//        int amountLike = thePost.getAmountLike();
//
//        Title theTitle = null;
//
//        if (data.get("titleId") != null) {
//            int titleId = Integer.parseInt(data.get("titleId").toString());
//            theTitle = titleService.findById(titleId);
//
//            if (theTitle == null) {
//                throw new RuntimeException("title's not found");
//            }
//        } else {
//            theTitle = thePost.getTitle();
//        }
//
//        String content = null;
//
//        if (data.get("content") != null) {
//            content = data.get("content").toString();
//        } else {
//            content = thePost.getContent();
//        }
//
//        Post newPost = new Post(postId, content, amountLike, theTitle);
//
//        Post dbPost = postService.save(newPost);
//
//        return dbPost;
        String action = "update_post";
        producerService.sendMessage(data, action);

        return "Update Successfully";
    }

    @PutMapping("/post/like-post")
    public String likePost(@RequestBody String data) {

//        int postId = Integer.parseInt(data.get("postId").toString());
//
//        Post thePost = postService.findById(postId);
//        if (thePost == null) {
//            throw new RuntimeException("post's not found");
//        }
//        thePost.setAmountLike(thePost.getAmountLike() + 1);
//
//        return postService.save(thePost);
        String action = "like_post";
        producerService.sendMessage(data, action);

        return "Like Successfully";
    }

}
