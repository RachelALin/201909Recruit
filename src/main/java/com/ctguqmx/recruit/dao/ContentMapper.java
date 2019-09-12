package com.ctguqmx.recruit.dao;

import com.ctguqmx.recruit.pojo.Content;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentMapper {
    public Content selectById(Integer id);
    List<Content> selectAll();
}
