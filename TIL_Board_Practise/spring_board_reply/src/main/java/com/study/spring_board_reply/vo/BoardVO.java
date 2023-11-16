package com.study.spring_board_reply.vo;

import lombok.Data;

/**
 * boardNo : 글 고유 번호
 * boardTitle : 게시글 제목
 * boardContent : 게시글 내용
 * boardWriteDate : 게시글 작성일
 * boardUpdateDate : 게시글 수정일
 * boardParents : 부모글 번호
 * boardDepth : 글 깊이 (답글 기능에 활용)
 */
@Data
public class BoardVO {
    private int boardNo;
    private String boardTitle;
    private String boardContent;
    private String boardWriteDate;
    private String boardUpdateDate;
    private int boardParents;
    private int boardDepth;
}