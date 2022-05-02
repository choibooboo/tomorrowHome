package com.itwill.tomorrowHome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.tomorrowHome.dao.MemberDao;
import com.itwill.tomorrowHome.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;
	
	/*
	 * 회원가입 
	 */
	@Override
	public int createMember(Member member) throws Exception {
		//1.아이디중복체크
		if(memberDao.existedMember(member.getM_id())) {
			//아이디중복
			return -1;
		}else {
			//가입
			int insertRowCount=memberDao.createMember(member);
			return insertRowCount;
		}
		
	}
	/*
	 * 회원 비밀번호 변경
	 */
	@Override
	public int updateMemberPassword(Member member) throws Exception {
		return memberDao.updateMemberPassword(member);
	}
	/*
	 * 회원 로그인
	 */
	@Override
	public int loginMember(String m_id, String m_password) throws Exception {
		/*
		 *  1:성공
		 * -1:비밀번호 불일치
		 * -2:존재하지 않는 회원
		 */
		int result=0;
		if (memberDao.existedMember(m_id)) {
			// 아이디 존재
			Member member = memberDao.findMember(m_id);
			if (member.getM_password().equals(m_password)) {
				// 비밀번호 일치
				result = 1;
			} else {
				// 비밀번호 불일치
				result = -1;
			}
		} else {
			// 회원존재 x
			result = -2;
		}
		
		/*
		if(memberDao.existedMember(m_id)) {
			//아이디 존재
			Member member = memberDao.findMember(m_id);
			if(member==null) {
				//아이디 존재x
				result=0;
			}else {
				//아이디 존재o
				if(member.getM_password().equals(m_password)) {
					//비밀번호o
					result=1;
				}else {
					//비밀번호x
					result=0;
				}
				
			}
		}
		*/
		
		return result;
	}
	
	/*
	 * 회원 정보 수정
	 */
	@Override
	public int updateMember(Member member) throws Exception { 
		return memberDao.updateMember(member);
	}
	/*
	 * 회원 탈퇴
	 */
	@Override
	public int deleteMember(String m_id) throws Exception {
		return memberDao.deleteMember(m_id);
	}
	/*
	 * 회원 정보 찾기
	 */
	@Override
	public Member findMember(String m_id) throws Exception {
		return memberDao.findMember(m_id);
	}
	/*
	 * 회원 아이디 중복 확인
	 */
	@Override
	public boolean existedMember(String m_id) throws Exception {
		boolean isExist = memberDao.existedMember(m_id);
		if(isExist) {
			return true;
		}else {
			return false;
		}
	}
}
