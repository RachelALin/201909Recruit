package com.ctguqmx.recruit.service.Impl;

import com.ctguqmx.recruit.dao.ContentMapper;
import com.ctguqmx.recruit.pojo.Content;
import com.ctguqmx.recruit.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public Content findById(Integer id) {
        return contentMapper.selectById(id);
    }

    @Override
    public List<Content> findAll() {
        return contentMapper.selectAll();
    }
}
