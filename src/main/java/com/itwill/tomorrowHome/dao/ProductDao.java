package com.itwill.tomorrowHome.dao;

import java.util.List;
import java.util.Map;

import com.itwill.tomorrowHome.domain.Product;

public interface ProductDao {
	/*
	 *  상품리스트 조회
	 */
	List<Product> selectProductList(Map<String, String> map) throws Exception;
	
	/*
	 * 상품 상세정보 조회
	 */
	Product selectProduct(int p_no) throws Exception;

	/*
	 * 상품 평균 평점 수정 
	 */
	int updateProductAvgScore(double avg_score, int p_no) throws Exception;

	/*
	 * 상품 조회수 증가
	 */
	int updateProductClickCount(int p_no) throws Exception;

	/*
	 * 상품 전체 개수 조회
	 */
	int selectProductListCount(Map<String, String> map);

	/*
	 * 멤버가 가장 선호하는 상품 컨셉 조회
	 */
	List<String> selectMemberBestProductConcept(String m_id);

	/*
	 * 인기상품 혹은 추천상품 리스트 조회
	 */
	List<Product> selectMainProductList(String concept, int count);

	
}