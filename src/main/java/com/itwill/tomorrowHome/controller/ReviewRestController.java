package com.itwill.tomorrowHome.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.tomorrowHome.controller.interceptor.LoginCheck;
import com.itwill.tomorrowHome.domain.Product;
import com.itwill.tomorrowHome.domain.Review;
import com.itwill.tomorrowHome.service.ProductService;
import com.itwill.tomorrowHome.service.ReviewService;

@RestController
public class ReviewRestController {
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private ProductService productService;
	
	@LoginCheck
	@PostMapping("/write_review")
	public Map<String, Object> review_insert(@ModelAttribute Review review) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			/*
			 *  1 : 성공 
			 * -1 : 상품 주문 X 
			 * -2 : 모든 리뷰 이미 작성 
			 */
			int result = reviewService.insertProductReview(review);
			if (result == 1) {
				review.setR_no(reviewService.selectMemberMaxRno(review.getM_id()));
				review.setR_date(new Date());
				resultMap.put("errorCode", 1);
				resultMap.put("errorMsg", "리뷰를 등록하였습니다");
				resultMap.put("data", review);
			} else if (result == -1) {
				resultMap.put("errorCode", -1);
				resultMap.put("errorMsg", "주문하신 상품의 리뷰만 작성이 가능합니다");
			} else {
				resultMap.put("errorCode", -2);
				resultMap.put("errorMsg", "리뷰를 이미 작성하셨습니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -3);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}

	/*
	 * 리뷰 삭제
	 */
	@LoginCheck
	@PostMapping("/delete_review")
	public Map<String, Object> review_delete(@RequestParam Integer r_no) {
		Map<String, Object> resultMap = new HashMap<>();
		if (r_no == null) {
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "잘못된 접근입니다");
			return resultMap;
		}
		try {
			int result = reviewService.deleteReview(r_no);
			if (result == 1) {
				resultMap.put("errorCode", 1);
				resultMap.put("errorMsg", "리뷰가 삭제되었습니다");
			} else {
				resultMap.put("errorCode", -2);
				resultMap.put("errorMsg", "리뷰가 삭제되지 않았습니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -3);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}

	/*
	 * 리뷰 수정폼
	 */
	@LoginCheck
	@PostMapping("/update_review_form")
	public Map<String, Object> update_review_form(@RequestParam Integer r_no) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			Review review = reviewService.selectProductReview(r_no);
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "성공");
			resultMap.put("data", review);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}

	/*
	 * 리뷰 수정
	 */
	@LoginCheck
	@PostMapping("/update_review")
	public Map<String, Object> update_review(@ModelAttribute Review review) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			int result = reviewService.updateReview(review);
			if (result == 1) {
				resultMap.put("errorCode", 1);
				resultMap.put("errorMsg", "리뷰를 수정하였습니다");
				resultMap.put("data", reviewService.selectProductReview(review.getR_no()));
			} else {
				resultMap.put("errorCode", -1);
				resultMap.put("errorMsg", "리뷰가 수정되지 않았습니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}

	/*
	 * 상품의 리뷰 정보를 반환
	 */
	@LoginCheck
	@PostMapping("/update_review_info")
	public Map<String, Object> update_review_info(@RequestParam Integer p_no) {
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> dataMap = new HashMap<>();
		if (p_no == null) {
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "잘못된 접근입니다");
			return resultMap;
		}
		try {
			Product product = productService.selectProductDetail(p_no);
			dataMap.put("count", product.getReviewList().size());
			dataMap.put("avg_score", product.getP_avg_score());
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "성공");
			resultMap.put("data", dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}

}
