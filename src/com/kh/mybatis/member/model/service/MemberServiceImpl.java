package com.kh.mybatis.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.template.Template;
import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;

public class MemberServiceImpl implements MemberService {

	@Override
	public int insertMember(Member m) {
		
		/*
		 * 기존 JDBC 때의 흐름
		 * 
		 * Connection conn = JDBCTemplate.getConnection();
		 * 
		 * int result = new MemberDao().insertMember(conn, m);
		 * 
		 * if(result > 0) {
		 * 		JDBCTemplate.commit();
		 * } else {
		 * 		JDBCTemplate.rollback();
		 * }
		 * 
		 * JDBCTempalte.close(conn);
		 * 
		 * return result;
		 * 
		 */
		
		// 1. SqlSession 객체 생성
		SqlSession sqlSession = Template.getSqlSession();
		
		// 2. 만들어진 SqlSession 객체와 전달값을 DAO단으로 넘기면서 요청 후 결과 받기
		int result = new MemberDao().insertMember(sqlSession, m);
		
		// 3. 전달받은 값 기준으로 트랜잭션 처리
		if(result > 0) { // 성공(커밋)
			sqlSession.commit();
		}
		
		// 고민해 볼 것: 이 시점에서 result == 0 이라면 아무것도 insert 되지 않은 것
		//			  현재 MEMBER 테이블의 내용물에는 변화가 없음
		// 단일 프로세스: 한 트랜잭션의 쿼리문이 한 개일 경우에는 실패 시 롤백을 "굳이 하지 않아도" 됨! (== 롤백 구문 작성해도 문제는 없음)
		//        => 한 트랜잭션의 쿼리문이 여러 개일 경우에는 반드시 롤백 처리 구문을 작성해야 함! 
		
		// 4. sqlSession 객체 반납
		sqlSession.close();
		
		// 5. 결과 반환
		return result;
	}

	@Override
	public Member loginMember(Member m) {
		
		// 1. SqlSession 객체 생성
		SqlSession sqlSession = Template.getSqlSession();
		
		// 2. 생성된 SqlSession 객체와 전달값을 Dao로 넘기면서 요청 후 결과 받기
		Member loginUser = new MemberDao().loginMember(sqlSession, m);
		
		// 3. 결과에 따른 트랜잭션 처리 => SELECT문을 실행해서 패스
		// 4. SqlSession 객체를 반납
		sqlSession.close();
		
		// 5. 결과 반환
		return loginUser;
		
	}

	@Override
	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

}
