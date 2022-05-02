package com.itwill.tomorrowHome.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.itwill.tomorrowHome.domain.Product;
import com.itwill.tomorrowHome.util.PageMakerDto;

@Transactional
public interface ProductService {
	/*
	 * 상품리스트 조회
	 */
	PageMakerDto<Product> selectProductList(Map<String, String> searchMap) throws Exception;

	/*
	 * 상품 상세정보 조회 
	 */
	Product selectProductDetail(int p_no) throws Exception;

	/*
	 * 인기상품 혹은 추천상품 리스트 조회
	 */
	Map<String, List<Product>>  selectMainProductList(String m_id);

}