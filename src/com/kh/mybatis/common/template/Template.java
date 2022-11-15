package com.kh.mybatis.common.template;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// 기존의 JDBCTemplate 클래스의 역할을 대신 수행하는 클래스
public class Template {
	
	/*
	 * 기존 JDBCTemplate 클래스의 메소드들
	 * 
	 * public static Connection getConnection() {
	 * 		driver.properties 라는 파일을 읽어들여서
	 * 		해당 DB와 접속된 Connection 객체를 생성해 반환
	 * }
	 * 
	 * public static void close(Connection/Statement/ResultSet) {
	 * 		전달받은 JDBCT용 객체를 반납시키는 구문 (오버로딩)
	 * }
	 * 
	 * public static void commit/rollback(Connection) {
	 * 		트랜잭션 처리
	 * }
	 * 
	 */
	
	// 마이바티스용 메소드
	public static SqlSession getSqlSession() {
		
		// mybatis-config.xml 파일을 읽어들여서 해당 DB와 접속된 SqlSession 객체를 생성해서 반환 
		SqlSession sqlSession = null;
		
		String resource = "/mybatis-config.xml"; // 슬래시(/) 는 최상위 폴더를 의미(resources 폴더)
		
		// SqlSession 객체 생성 단계
		// SqlSession 객체를 생성하기 위해서는 SqlSessionFactory 객체가 필요
		// SqlSessionFactory 객체를 생성하기 위해서는 SqlSessionFactoryBuilder 객체가 필요
		
		try {
			// 1. 자원으로부터 통로를 얻어내겠음 (InputStream 객체 생성)
			InputStream stream = Resources.getResourceAsStream(resource);
			
			// 2. SqlSessionFactoryBuilder 객체 생성
			// => 기본 생성자로 생성
			SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
			
			// 3. SqlSessionFactory 객체 생성
			// => sqlSessionFactoryBuilder 객체로부터 build 메소드 호출
			SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
			// 이 단계에서 mybatis-config.xml 파일이 읽힘
			
			// 4. SqlSession 객체 생성
			// => sqlSessionFactory 객체로부터 openSession 메소드 호출
			sqlSession = sqlSessionFactory.openSession(false);
			// openSession 메소드
			// : 자동 commit을 할 건지 안 할 건지에 대한 여부를 지정
			// => false == 자동 commit을 하지 않겠음 (개발자가 직접 commit을 하겠다)
			// 			     개발자 문서의 예시처럼 매개변수 생략 시 기본값은 false임!
			// => true == 자동 commit을 하겠음
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSession;

	}

}
