package com.itwill.tomorrowHome.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.tomorrowHome.domain.Member;
@Repository
public class MemberDaoImpl implements MemberDao{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int createMember(Member member) throws Exception {
		return sqlSession.insert("mapper.MemberMapper.createMember",
				member);
	}

	@Override
	public int updateMemberPassword(Member member) throws Exception {
		return sqlSession.update("mapper.MemberMapper.updateMemberPassword",
				member);
	}

	@Override
	public int updateMember(Member member) throws Exception {
		return sqlSession.update("mapper.MemberMapper.updateMember",
				member);
	}

	@Override
	public int deleteMember(String m_id) throws Exception {
		return sqlSession.delete("mapper.MemberMapper.deleteMember",
				m_id);
	}

	@Override
	public Member findMember(String m_id) throws Exception {
		return sqlSession.selectOne("mapper.MemberMapper.findMember",
				m_id);
	}

	@Override
	public boolean existedMember(String m_id) throws Exception {
		int count = sqlSession.selectOne("mapper.MemberMapper.existedMember", m_id);
		if(count>0) {
			return true;
		}else {
			return false;
		}
	}
	
}
