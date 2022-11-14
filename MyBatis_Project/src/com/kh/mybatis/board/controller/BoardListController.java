package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.model.service.BoardServiceImpl;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.model.vo.PageInfo;
import com.kh.mybatis.common.template.Pagination;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 페이징 처리를 위한 변수 먼저 세팅 후 PageInfo로 가공 처리
	    int listCount = new BoardServiceImpl().selectListCount(); // 현재 총 게시글 개수
	    int currentPage = Integer.parseInt(request.getParameter("currentPage")); // 현재 요청한 페이지(즉, 사용자가 요청한 페이지 수)
	    int pageLimit = 10; // 페이지 하단에 보여질 페이징바의 페이지 최대 개수
	    int boardLimit = 5; // 한 페이지에 보여질 게시글의 최대 개수(몇 개 단위씩 리스트가 보여질 건지)
	    
	    PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
	    
	    // listCount 값을 return 받았으므로 완성된 PageInfo 객체가 담겨 있을 것
	    // System.out.println(pi);
	    // PageInfo [listCount=14, currentPage=1, pageLimit=10, boardLimit=5, maxPage=3, startPage=1, endPage=3]
	    
	    // 여기부터 전체 리스트 조회를 위한 코드
	    ArrayList<Board> list = new BoardServiceImpl().selectList(pi);
	    
	    // 응답 페이지로 보낼 변수
	    request.setAttribute("pi", pi); // 페이징 바를 만들기 위한 변수
	    request.setAttribute("list", list); // 조회된 내용물을 화면에 뿌려 주기 위한 변수
	    
	    // 포워딩
	    request.getRequestDispatcher("WEB-INF/views/board/boardListView.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
