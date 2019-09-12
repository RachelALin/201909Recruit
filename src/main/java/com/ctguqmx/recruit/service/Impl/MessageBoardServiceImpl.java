package com.ctguqmx.recruit.service.Impl;

import com.ctguqmx.recruit.dao.MessageBoardMapper;
import com.ctguqmx.recruit.pojo.MessageBoard;
import com.ctguqmx.recruit.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alin
 * @packagename com.ctguqmx.recruit.service.Impl
 * @date 2019/8/15
 * @time 17:03
 */
@Service
public class MessageBoardServiceImpl implements MessageBoardService {
    @Autowired
    private MessageBoardMapper messageBoardMapper;
    @Override
    public List<MessageBoard> FindAll() {
        return messageBoardMapper.FindAll();
    }
}
