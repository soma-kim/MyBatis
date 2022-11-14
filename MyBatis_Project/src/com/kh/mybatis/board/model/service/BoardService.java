package com.kh.mybatis.board.model.service;

import java.util.ArrayList;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.model.vo.PageInfo;

public interface BoardService {
	
	// 게시글 리스트 조회
	int selectListCount();
	
	ArrayList<Board> selectList(PageInfo pi);
	
	// 게시글 상세 조회
	int increaseCount(int boardNo);
	
	Board selectBoard(int boardNo);
	
	// 댓글 조회
	ArrayList<Reply> selectReplyList(int boardNo);

}
