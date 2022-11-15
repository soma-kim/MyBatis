package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
 * Servlet implementation class BoardSearchController
 */
@WebServlet("/search.bo")
public class BoardSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 검색 요청 시 전달값 뽑기: 동적 쿼리를 이용한 방식
		// String condition = request.getParameter("condition"); // condition : 검색 조건("writer" / "title" / "content")
		// String keyword = request.getParameter("keyword"); // keyword : 검색어("사용자가 입력한 키워드값")
		
		// 검색 요청 시 전달값 뽑기: 정적 쿼리를 이용한 방식
		String condition = request.getParameter("condition"); // condition: 검색 조건("USER_ID" / "BOARD_TITLE" / "BOARD_CONTENT")
		String keyword = request.getParameter("keyword"); // keyword: 검색어 ("사용자가 입력한 키워드값")
		
		// 마이바티스에서는 미완성된 쿼리문 부분이 여러 개일지라도 하나의 매개변수로 가공해서 구멍을 매꿔 줘야 하는 것이 원칙!
		// => 그래서 항상 VO로 가공해서 보냈던 것!
		// => 하지만 만약 굳이 VO로 가공하지 않아도 될 것 같다면? HashMap 타입으로 가공하면 됨!
		HashMap<String, String> map = new HashMap<>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		// 페이징 처리를 위한 기본 변수 4가지
		// int searchCount = new BoardService().selectSearchCount(condition, keyword); // 현재 검색결과에 맞는 게시글의 총 개수
		// 마이바티스에서는 구멍이 여러 개라도 VO타입 하나로 가공해서 보내는 것이 원칙!
		int searchCount = new BoardServiceImpl().selectSearchCount(map);
		int currentPage = Integer.parseInt(request.getParameter("currentPage")); // currentPage = 1 : 페이징 처리를 위한 사용자가 요청한 페이지
		int pageLimit = 10;
		int boardLimit = 5;
		
		PageInfo pi = Pagination.getPageInfo(searchCount, currentPage, pageLimit, boardLimit);
		
		// System.out.println(pi);
		// 작성자: user // PageInfo [listCount=7, currentPage=1, pageLimit=10, boardLimit=5, maxPage=2, startPage=1, endPage=2]
		// 제목: 입니다   // PageInfo [listCount=1, currentPage=1, pageLimit=10, boardLimit=5, maxPage=1, startPage=1, endPage=1]
		// 내용: 게시판   // PageInfo [listCount=5, currentPage=1, pageLimit=10, boardLimit=5, maxPage=1, startPage=1, endPage=1]
		
		ArrayList<Board> list = new BoardServiceImpl().selectSearchList(map, pi);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		// 검색창에 검색어를 유지시키기 위해 추가하여 jsp로 보내 줌!
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		
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
