package com.ctguqmx.recruit.service;

import com.ctguqmx.recruit.pojo.Content;

import java.util.List;

public interface ContentService {
    public Content findById(Integer id);
    public List<Content> findAll();
}
