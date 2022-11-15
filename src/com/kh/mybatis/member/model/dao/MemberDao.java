package com.kh.mybatis.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDao {
	
	public int insertMember(SqlSession sqlSession, Member m) {
		
		/*
		 * 기존의 JDBC 코드
		 * 
		 * int result = 0;
		 * PreparedStatement pstmt = null;
		 * 
		 * String sql = prop.getProperty("insertMember");
		 * 
		 * try {
		 * 
		 * 		pstmt = conn.prepareStatement(sql);
		 * 		pstmt.setString(1, m.getUserId());
		 * 		...
		 * 		
		 * 		result = pstmt.executeUpdate();
		 * 
		 * } catch(xxx) {
		 * 
		 * } finally {
		 * 
		 * 		JDBCTemplate.close(pstmt);
		 * 
		 * }
		 * 
		 * return result;
		 * 
		 */
		
		/*
		 * sqlSession 객체에서 제공하는 메소드를 통해 sql문을 찾고 실행해서 결과까지 받아 볼 수 있음
		 * 
		 * [ 표현법 ]
		 * sqlSession.sql문종류에맞는메소드("매퍼파일의namespace.해당sql문의id속성값", sql문을완성시킬데이터값);
		 * 
		 * => INSERT 구문을 실행하고자 할 때 : sqlSession.insert() 메소드
		 * => UPDATE 구문을 실행하고자 할 때 : sqlSession.update() 메소드
		 * => DELETE 구문을 실행하고자 할 때 : sqlSession.delete() 메소드
		 * 
		 * => 해당 sql문이 미완성된 상태가 아니라면 "sql문을완성시킬데이터값" 매개변수는 생략 가능
		 * 
		 */
		
		// int result = sqlSession.insert("memberMapper.insertMember", m);
		// return result;
		
		// 이렇게 1줄로도 표현 가능!
		return sqlSession.insert("memberMapper.insertMember", m);
		
	}
	
	public Member loginMember(SqlSession sqlSession, Member m) {
		
		/*
		 * SELECT문을 실행할 수 있는 메소드
		 * => 단일 행 조회: selectOne()
		 * => 여러 행 조회: selectList()
		 */
		
		Member loginUser = sqlSession.selectOne("memberMapper.loginMember", m);
		// 만약 조회된 게 없다면 null이 담겨 있을 것
		
		return loginUser;
		
	}

}
