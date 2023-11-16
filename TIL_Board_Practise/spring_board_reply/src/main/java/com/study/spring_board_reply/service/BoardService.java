package com.study.spring_board_reply.service;

import com.study.spring_board_reply.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardDAO boardDAO;

    public List getList(){
        return boardDAO.getList();
    }
}
