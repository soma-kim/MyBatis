package com.kh.mybatis.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.model.vo.PageInfo;

public class BoardDao {
	
	public int selectListCount(SqlSession sqlSession) {
		
		return sqlSession.selectOne("boardMapper.selectListCount");
		// 어느 mapper 타입의 어느 id를 가진 쿼리문을 찾아서 실행할 것인지?
		
	}
	
	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi) {
		
		/*
		 * * 마이바티스에서는 페이징 처리를 위해 RowBounds라는 클래스를 제공함
		 *   RowBounds라는 객체를 생성할 경우 필요한 값이 2개가 있음
		 *   - offSet: 0번째에서부터 몇 개의 행을 건너뛰고 조회할 건지에 대한 값
		 *   - limit: 조회할 행의 개수에 대한 값 (== boardLimit 값)
		 *   ex) boardlimit가 5일 경우
		 *                                  offset(건너뛸 숫자)     limit(조회할 개수)
		 *   currentPage: 1 => 1  ~ 5             0                    5            
		 *   currentPage: 2 => 2  ~ 10            5                    5            
		 *   currentPage: 3 => 11 ~ 15            10                   5    
		 *   ...
		 *   => limit == boardLimit
		 *   => offset == (currentPage - 1) * limit        
		 */
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		// selectList 메소드 호출 시 RowBounds 객체를 넘겨야 할 경우
		// selectList 메소드의 오버로딩된 형태 중 매개변수가 3개인 메소드를 호출해야만 함
		// => 두 번째 매개변수 == 쿼리문을 완성시키기 위한 데이터 값
		// * 주의할 점
		// => 딱히 두 번째 매개변수 자리에 넘길 값이 없다면 null 값을 넘기면 됨!
		ArrayList<Board> list = (ArrayList)sqlSession.selectList("boardMapper.selectList", null, rowBounds);
		// 쿼리문 컬럼 중에서 BOARD 타입의 값이 아닌 MEMBER나 다른 곳의 컬럼이 1개라도 들어 있다면 오류남!
		// => 제네릭까지 형 변환 하지 말고 ArrayList까지만 형 변환!
		
		return list;
		
	}
	
	public int increaseCount(SqlSession sqlSession, int boardNo) {
		
		return sqlSession.update("boardMapper.increaseCount", boardNo);
		
	}
	
	public Board selectBoard(SqlSession sqlSession, int boardNo) {
		
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
		// 현재 Board 타입으로 mapper에서 반환되었음
		// 그대로 리턴!
		
	}
	
	public ArrayList<Reply> selectReplyList(SqlSession sqlSession, int boardNo) {
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList", boardNo);
		// 제네릭 설정까지 형 변환 하면 오류 남!
		
	}
	
	public int selectSearchCount(SqlSession sqlSession, HashMap<String, String> map) {
		
		return sqlSession.selectOne("boardMapper.selectSearchCount", map);
		
	}
	
	public ArrayList<Board> selectSearchList(SqlSession sqlSession, HashMap<String, String> map, PageInfo pi) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit); // 몇 개를 건너뛰고, 몇 개를 가지고 올 건지 범위 제시
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectSearchList", map, rowBounds);
		// 어떤 매퍼 타입의 어떤 쿼리문 실행할지? 구멍이 뚫렸을 때 매꿀 놈, rowBounds 자리
	}

}