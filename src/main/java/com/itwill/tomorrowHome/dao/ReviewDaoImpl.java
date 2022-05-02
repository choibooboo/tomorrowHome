package com.itwill.tomorrowHome.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.tomorrowHome.domain.Review;

@Repository
public class ReviewDaoImpl implements ReviewDao {
	@Autowired
	private SqlSession sqlSession;

	/*
	 * 상품의 리뷰리스트 조회
	 */
	@Override
	public List<Review> selectProductReviewList(int p_no) throws Exception {
		return sqlSession.selectList("mapper.reviewMapper.selectProductReviewList", p_no);
	}

	/*
	 * 특정 리뷰 정보 조회
	 */
	@Override
	public Review selectProductReview(int r_no) throws Exception {
		return sqlSession.selectOne("mapper.reviewMapper.selectProductReview", r_no);
	}

	/*
	 * 특정 상품 리뷰 평점 조회
	 */
	@Override
	public double selectProductReviewAvgScore(int p_no) throws Exception {
		return sqlSession.selectOne("mapper.reviewMapper.selectProductReviewAvgScore", p_no);
	}

	/*
	 * 상품 리뷰 등록
	 */
	@Override
	public int insertProductReview(Review review) throws Exception {
		return sqlSession.insert("mapper.reviewMapper.insertProductReview", review);
	}

	/*
	 * 회원의 특정상품 리뷰 작성 여부 조회
	 */
	@Override
	public int selectMemberProductReviewCount(String m_id, int p_no) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("m_id", m_id);
		map.put("p_no", p_no);
		return sqlSession.selectOne("mapper.reviewMapper.selectMemberProductReviewCount", map);
	}

	/*
	 * 리뷰 수정
	 */
	@Override
	public int updateReview(Review review) throws Exception {
		return sqlSession.update("mapper.reviewMapper.updateReview", review);
	}

	/*
	 * 리뷰 삭제
	 */
	@Override
	public int deleteReview(int r_no) throws Exception {
		return sqlSession.update("mapper.reviewMapper.deleteReview", r_no);
	}

	/*
	 * 멤버의 가장 최근 작성 리뷰번호
	 */
	@Override
	public int selectMemberMaxRno(String m_id) throws Exception {
		return sqlSession.selectOne("mapper.reviewMapper.selectMemberMaxRno", m_id);
	}

}
