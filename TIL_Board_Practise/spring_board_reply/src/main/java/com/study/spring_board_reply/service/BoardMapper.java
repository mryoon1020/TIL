package com.study.spring_board_reply.service;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardMapper {

    List getList();

}
