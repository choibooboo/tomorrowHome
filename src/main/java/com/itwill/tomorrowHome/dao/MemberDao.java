package com.itwill.tomorrowHome.dao;

import com.itwill.tomorrowHome.domain.Member;

public interface MemberDao {
	
	/*
	 * 멤버 생성
	 */
	int createMember(Member member) throws Exception;
	/*
	 * 멤버 비밀번호 변경
	 */
	int updateMemberPassword(Member member) throws Exception;
	/*
	 * 멤버 정보 수정
	 */
	int updateMember(Member member) throws Exception;
	/*
	 * 멤버 삭제
	 */
	int deleteMember(String m_id) throws Exception;
	/*
	 * 멤버 정보 조회
	 */
	Member findMember(String m_id) throws Exception;
	/*
	 * 회원 아이디 존재확인(아이디 중복체크 위해 필요)
	 */
	boolean existedMember(String m_id) throws Exception;
	
	
	
	
}
