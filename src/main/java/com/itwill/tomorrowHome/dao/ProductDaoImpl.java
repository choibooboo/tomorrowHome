package com.itwill.tomorrowHome.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.tomorrowHome.domain.Product;

@Repository
public class ProductDaoImpl implements ProductDao { 
	@Autowired
	private SqlSession sqlSession;

	/*
	 * 상품리스트 조회
	 */
	@Override 
	public List<Product> selectProductList(Map<String, String> map) throws Exception { 
		return sqlSession.selectList("mapper.productMapper.selectProductList", map);
	}

	/*
	 * 상품 상세정보 조회
	 */
	@Override
	public Product selectProduct(int p_no) throws Exception {  
		return sqlSession.selectOne("mapper.productMapper.selectProduct", p_no);
	}

	/*
	 * 상품 평균 평점 수정
	 */
	@Override
	public int updateProductAvgScore(double avg_score, int p_no) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("avg_score", avg_score);
		map.put("p_no", p_no);
		return sqlSession.update("mapper.productMapper.updateProductAvgScore", map);
	}
	
	/*
	 * 상품 조회수 증가
	 */
	@Override
	public int updateProductClickCount(int p_no) throws Exception {
		return sqlSession.update("mapper.productMapper.updateProductClickCount", p_no);
	}
	
	/*
	 * 상품 전체 개수 조회
	 */
	@Override
	public int selectProductListCount(Map<String, String> map) {
		return sqlSession.selectOne("mapper.productMapper.selectProductListCount", map);
	}

	/*
	 * 멤버가 가장 선호하는 상품 컨셉 조회
	 */
	@Override
	public List<String> selectMemberBestProductConcept(String m_id) {
		return sqlSession.selectList("mapper.productMapper.selectMemberBestProductConcept", m_id);
	}
	
	/*
	 * 인기상품 혹은 추천상품 리스트 조회
	 */
	@Override
	public List<Product> selectMainProductList(String concept, int count) {
		Map<String, Object> map = new HashMap<>();
		map.put("p_concept", concept);
		map.put("count", count);
		return sqlSession.selectList("mapper.productMapper.selectMainProductList", map);
	}

}
