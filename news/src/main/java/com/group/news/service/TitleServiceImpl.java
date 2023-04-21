package com.group.news.service;

import com.group.news.dao.TitleRepository;
import com.group.news.entity.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitleServiceImpl implements TitleService{

    private TitleRepository titleRepository;

    @Autowired
    public TitleServiceImpl (TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @Override
    public List<Title> findAll() {
        return titleRepository.findAll();
    }

    @Override
    public Title findById(int Id) {
        Optional<Title> result = titleRepository.findById(Id);

        Title theTitle = null;

        if (result.isPresent()) {
            theTitle = result.get();
        } else {
            throw new RuntimeException("Did not find employee");
        }

        return theTitle;
    }
}
