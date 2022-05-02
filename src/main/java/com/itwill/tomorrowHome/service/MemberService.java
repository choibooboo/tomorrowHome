package com.itwill.tomorrowHome.service;

import org.springframework.transaction.annotation.Transactional;

import com.itwill.tomorrowHome.domain.Member;

@Transactional
public interface MemberService {
	
	/*
	 * 회원 가입
	 */
	int createMember(Member member) throws Exception;
	/*
	 * 회원 로그인
	 */
	int loginMember(String m_id, String password) throws Exception;
	/*
	 * 회원 비밀번호 변경
	 */
	int updateMemberPassword(Member member) throws Exception;
	/*
	 * 회원 정보 수정
	 */
	int updateMember(Member member) throws Exception;
	/*
	 * 회원 탈퇴
	 */
	int deleteMember(String m_id) throws Exception;
	/*
	 * 회원 정보 찾기
	 */
	Member findMember(String m_id) throws Exception;
	/*
	 * 회원 아이디 중복 확인
	 */
	boolean existedMember(String m_id) throws Exception;
}
