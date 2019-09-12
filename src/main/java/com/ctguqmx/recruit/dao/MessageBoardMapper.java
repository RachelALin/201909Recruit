package com.ctguqmx.recruit.dao;

import com.ctguqmx.recruit.pojo.MessageBoard;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alin
 * @packagename com.ctguqmx.recruit.dao
 * @date 2019/8/15
 * @time 16:56
 */
@Repository
public interface MessageBoardMapper {

    List<MessageBoard> FindAll();
}
