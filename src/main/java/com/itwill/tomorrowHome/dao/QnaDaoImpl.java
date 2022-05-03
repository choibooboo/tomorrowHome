package com.itwill.tomorrowHome.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.tomorrowHome.domain.Qna;

@Repository
public class QnaDaoImpl implements QnaDao {
	@Autowired
	private SqlSession sqlSession;

	/*
	 * 새로운 게시물을 추가
	 */
	@Override
	public int insertNewQna(Qna qna) throws Exception {
		return sqlSession.insert("mapper.qnaMapper.insertNewQna", qna);
	}

	/*
	 * 게시물 번호에 해당하는 게시물 정보를 반환
	 */
	@Override
	public Qna selectQna(int q_no) throws Exception {
		return sqlSession.selectOne("mapper.qnaMapper.selectQna", q_no);
	}

	/*
	 * 답글 게시물을 추가
	 */
	@Override
	public int insertReply(Qna qna) throws Exception {
		// 댓글을 작성할 대상글(원글)의 정보를 조회
		Qna temp = sqlSession.selectOne("mapper.qnaMapper.selectQna", qna.getQ_no());
		// 영향을 받는 기존 글들의 논리적인 순서 번호 변경
		Map<String, Object> map = new HashMap<>();
		map.put("q_step", temp.getQ_step());
		map.put("q_group_no", temp.getQ_group_no());
		sqlSession.update("mapper.qnaMapper.updateStep", map);
		// 댓글 삽입
		map = new HashMap<>();
		map.put("q_title", qna.getQ_title());
		map.put("q_content", qna.getQ_content());
		map.put("q_group_no", temp.getQ_group_no()); 
		map.put("q_step", temp.getQ_step() + 1); 
		map.put("q_depth", temp.getQ_depth() + 1); 
		map.put("m_id", qna.getM_id());
		return sqlSession.insert("mapper.qnaMapper.insertReply", map);
	}

	/*
	 * 게시물 리스트를 반환(게시물시작번호,게시물끝번호)
	 */
	@Override
	public List<Qna> selectQnaList(int start, int last, String search_type, String search_value) throws Exception {
		System.out.println("" + start + " ~ " + last);
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("last", last);
		map.put("search_type", search_type);
		map.put("search_value", search_value);
		return sqlSession.selectList("mapper.qnaMapper.selectQnaList", map);
	}

	/*
	 * 게시물을 삭제
	 */
	@Override
	public int deleteQna(int q_no) throws Exception {
		return sqlSession.delete("mapper.qnaMapper.deleteQna", q_no);
	}

	/*
	 * 게시물내용을 수정
	 */
	@Override
	public int updateQna(Qna qna) throws Exception {
		return sqlSession.update("mapper.qnaMapper.updateQna", qna);
	}

	/*
	 * 게시물 조회수를 1 증가
	 */
	@Override
	public void updateReadCount(int q_no) throws Exception {
		sqlSession.update("mapper.qnaMapper.updateReadCount", q_no);
	}

	/*
	 * 게시물 총 건수를 조회, 반환
	 */
	@Override
	public int selectQnaCount(String search_type, String search_value) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("search_type", search_type);
		map.put("search_value", search_value);
		return sqlSession.selectOne("mapper.qnaMapper.selectQnaCount", map);
	}
}
