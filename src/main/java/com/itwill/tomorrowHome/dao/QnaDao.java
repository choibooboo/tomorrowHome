package com.itwill.tomorrowHome.dao;

import java.util.List;

import com.itwill.tomorrowHome.domain.Qna;

public interface QnaDao {

	/*
	 * 새로운 게시물을 추가
	 */
	int insertNewQna(Qna qna) throws Exception;

	/*
	 * 게시물 번호에 해당하는 게시물 정보를 반환
	 */
	Qna selectQna(int q_no) throws Exception;

	/*
	 * 답글 게시물을 추가
	 */
	int insertReply(Qna qna) throws Exception;

	/*
	 * 게시물 리스트를 반환(게시물시작번호,게시물끝번호)
	 */
	List<Qna> selectQnaList(int start, int last, String search_type, String search_value) throws Exception;

	/*
	 * 게시물을 삭제
	 */
	int deleteQna(int q_no) throws Exception;

	/*
	 * 게시물내용을 수정
	 */
	int updateQna(Qna qna) throws Exception;

	/*
	 * 게시물 조회수를 1 증가
	 */
	void updateReadCount(int q_no) throws Exception;

	/*
	 * 게시물 총 건수를 조회, 반환
	 */
	int selectQnaCount(String search_type, String search_value) throws Exception;

}