package com.study.spring_board_reply.controller;

import com.study.spring_board_reply.service.BoardService;
import com.study.spring_board_reply.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    private BoardService service;
    private BoardVO boardVO;

//    @PutMapping("/")
//    public String test(@RequestBody Map map){
//        return "Success";
//    }

    @GetMapping("/")
    public List<BoardVO> getlist(){
        List<BoardVO> list = service.getList();
        return list;
    }

}