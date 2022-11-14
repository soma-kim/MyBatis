package com.kh.mybatis.member.model.service;

import com.kh.mybatis.member.model.vo.Member;

// 인터페이스 : 상수 필드(public static final) + 추상 메소드 (public abstract, 몸통부가 없는 메소드)
public interface MemberService {
	
	// 회원가입용 메소드
	/* public abstract */ int insertMember(Member m);
	// 인터페이스는 어차피 추상 메소드만 정의가 가능하기 때문에 public abstract는 묵시적으로 생략!
	
	// 로그인용 메소드
	Member loginMember(Member m);
	
	// 회원 정보 수정용 메소드
	int updateMember(Member m);
	
	// 회원 탈퇴용 메소드
	int deleteMember(Member m);
	
	// ...
	// 보통 설계 단계에서 다음과 같이 인터페이스로 추상 메소드만 설계해 두고
	// 실제 개발 단계에서 해당 인터페이스를 구현하는 구현체 클래스를 만들어 메소드를 완성시켜 사용함!

}
