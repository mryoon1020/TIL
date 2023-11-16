package com.study.spring_board_reply.service;

import com.study.spring_board_reply.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("boardDAO")
public class BoardDAO {
    @Autowired
    private BoardMapper boardMapper;

    public List<BoardVO> getList(){
        return boardMapper.getList();
    }
}
