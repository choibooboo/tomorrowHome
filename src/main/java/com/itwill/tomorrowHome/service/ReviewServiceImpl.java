package com.itwill.tomorrowHome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.tomorrowHome.dao.OrderDao;
import com.itwill.tomorrowHome.dao.ProductDao;
import com.itwill.tomorrowHome.dao.ReviewDao;
import com.itwill.tomorrowHome.domain.Product;
import com.itwill.tomorrowHome.domain.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ProductDao productDao;

	/*
	 * 상품 리뷰 등록
	 */
	@Override
	public int insertProductReview(Review review) throws Exception {
		/*
		 * 1 : 성공 -1 : 상품 주문 X -2 : 모든 리뷰 이미 작성
		 */
		int result = 1;
		// 회원의 상품 주문여부 확인
		int orderCount = orderDao.selectMemberProductOrderCount(review.getM_id(), review.getP_no());
		// 상품을 주문했다면
		if (orderCount > 0) {
			// 회원의 특정 상품 리뷰 작성 여부 조회
			int reviewCount = reviewDao.selectMemberProductReviewCount(review.getM_id(), review.getP_no());
			// 리뷰 작성이 가능하다면
			if (orderCount > reviewCount) {
				// 리뷰 등록
				reviewDao.insertProductReview(review);
				updateProductReviewAvg(review.getP_no());
			} else {
				result = -2;
			}
		} else {
			result = -1;
		}
		return result;
	};

	/*
	 * 특정 리뷰 정보 조회
	 */
	@Override
	public Review selectProductReview(int r_no) throws Exception {
		return reviewDao.selectProductReview(r_no);
	};

	/*
	 * 리뷰 수정
	 */
	@Override
	public int updateReview(Review review) throws Exception {
		int result = reviewDao.updateReview(review);
		if (result == 1) {
			updateProductReviewAvg(review.getP_no());
		}
		return result;
	};

	/*
	 * 리뷰 삭제
	 */
	@Override
	public int deleteReview(int r_no) throws Exception {
		Review review = reviewDao.selectProductReview(r_no);
		int result = reviewDao.deleteReview(r_no);
		if(result == 1) { 
			updateProductReviewAvg(review.getP_no());
		}
		return result;
	};
	
	/*
	 * 멤버의 가장 최근 작성 리뷰번호
	 */
	@Override
	public int selectMemberMaxRno(String m_id) throws Exception { 
		return reviewDao.selectMemberMaxRno(m_id);
	}
	
	/*
	 * 상품의 리뷰평균 수정 
	 */
	public void updateProductReviewAvg(int p_no) throws Exception { 
		Double avg_score = 0.0;
		Product product = productDao.selectProduct(p_no);
		if(product.getReviewList().size() != 0) {
			avg_score = reviewDao.selectProductReviewAvgScore(p_no);
		}
		productDao.updateProductAvgScore(avg_score, p_no);
	}
	
}
