package com.itwill.tomorrowHome.service;

import org.springframework.transaction.annotation.Transactional;

import com.itwill.tomorrowHome.domain.Qna;
import com.itwill.tomorrowHome.util.PageMakerDto;

@Transactional
public interface QnaService {

	/*
	 * 새로운 게시물을 추가
	 */
	int insertNewQna(Qna qna) throws Exception;

	/*
	 * 게시물 번호에 해당하는 게시물 정보를 반환
	 */
	Qna selectQna(int q_no, String workType) throws Exception;

	/*
	 * 답글 게시물을 추가
	 */
	int insertReply(Qna qna) throws Exception;

	/*
	 * 게시물 리스트를 반환
	 */
	PageMakerDto<Qna> selectQnaList(int currentPage) throws Exception;

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
	int selectQnaCount() throws Exception;

}