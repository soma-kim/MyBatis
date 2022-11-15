package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.model.service.BoardService;
import com.kh.mybatis.board.model.service.BoardServiceImpl;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 글 번호 먼저 뽑기 (bno)
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		// 다형성을 적용
		BoardService boardService = new BoardServiceImpl();
		
		// 1. 조회수를 증가시키는 서비스 먼저 요청
		int result = boardService.increaseCount(boardNo);
		
		if(result > 0) { // 조회수가 성공적으로 증가되었다면
			
			// 2. 해당 게시글을 상세 조회하는 서비스 요청
			Board b = boardService.selectBoard(boardNo);
			
			// 3. 해당 게시글에 딸린 댓글들을 조회하는 서비스 요청
			// => 댓글 조회 기능을 동기식 방식으로 selectList 연습해 보기
			ArrayList<Reply> list = boardService.selectReplyList(boardNo);
			
			// 상세 조회 결과, 댓글 리스트 조회 결과 => request에 담기
			request.setAttribute("b", b);
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("WEB-INF/views/board/boardDetailView.jsp").forward(request, response);
			
		} else {
			
			// 에러 문구를 담아서 에러 페이지로 포워딩
			request.setAttribute("errorMsg", "게시글 상세 조회 실패");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
