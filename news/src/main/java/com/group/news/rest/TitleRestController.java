package com.group.news.rest;

import com.group.news.entity.Post;
import com.group.news.entity.Title;
import com.group.news.service.PostService;
import com.group.news.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/titles")
public class TitleRestController {
    private TitleService titleService;

    @Autowired
    public TitleRestController(TitleService titleService) {
        this.titleService = titleService;
    }

    @GetMapping("/all-titles")
    public List<Title> findAll() {
        return titleService.findAll();
    }
}
