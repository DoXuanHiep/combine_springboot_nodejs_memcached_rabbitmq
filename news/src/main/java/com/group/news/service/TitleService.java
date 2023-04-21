package com.group.news.service;

import com.group.news.entity.Title;

import java.util.List;


public interface TitleService {
    List<Title> findAll();

    Title findById(int Id);
}
