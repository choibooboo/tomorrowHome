package com.itwill.tomorrowHome.dao;

import java.util.List;

import com.itwill.tomorrowHome.domain.Review;

public interface ReviewDao {
	/*
	 * 상품의 리뷰리스트 조회
	 */
	List<Review> selectProductReviewList(int p_no) throws Exception;
	
	/*
	 * 상품 리뷰 등록 
	 */
	int insertProductReview(Review review) throws Exception;

	/*
	 * 회원의 특정상품 리뷰 작성 여부 조회
	 */
	int selectMemberProductReviewCount(String m_id, int p_no) throws Exception;

	/*
	 * 특정 리뷰 정보 조회
	 */
	Review selectProductReview(int r_no) throws Exception;

	/*
	 * 리뷰 수정 
	 */
	int updateReview(Review review) throws Exception;

	/*
	 * 리뷰 삭제
	 */
	int deleteReview(int r_no) throws Exception;

	/*
	 * 특정 상품 리뷰 평점 조회
	 */
	double selectProductReviewAvgScore(int p_no) throws Exception;

	/*
	 * 멤버의 가장 최근 작성 리뷰번호
	 */
	int selectMemberMaxRno(String m_id) throws Exception;

}