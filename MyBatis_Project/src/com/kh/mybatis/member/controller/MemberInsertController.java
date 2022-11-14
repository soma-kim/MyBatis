package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.member.model.service.MemberServiceImpl;
import com.kh.mybatis.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// post 방식의 경우 인코딩 설정 먼저
		request.setCharacterEncoding("UTF-8");
		
		// 요청 시 전달값 뽑기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		// VO 객체로 가공
		Member m = new Member(userId, userPwd, userName, email, birthday, gender, phone, address);
		
		// 서비스로 VO 객체를 넘겨서 요청 후 결과 받기
		int result = new MemberServiceImpl().insertMember(m);
		
		// 결과에 따른 응답페이지 지정
		if(result > 0) { // 성공 => 메인 페이지로 url 재요청
			
			response.sendRedirect(request.getContextPath());
			
		} else { // 실패 => 에러문구를 담아서 에러페이지로 포워딩
			
			request.setAttribute("errorPage", "회원가입 실패");
			request.getRequestDispatcher("WEB-INF/views/common/errePage.jsp").forward(request, response);
			
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
