package com.itwill.tomorrowHome.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.tomorrowHome.dao.ProductDao;
import com.itwill.tomorrowHome.domain.Product;
import com.itwill.tomorrowHome.util.PageMaker;
import com.itwill.tomorrowHome.util.PageMakerDto;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	/*
	 * 상품리스트 조회
	 */
	@Override
	public PageMakerDto<Product> selectProductList(Map<String, String> searchMap) throws Exception {
		/*
		 * Map<String, String> map = new HashMap<>(); 
		 * map.put("cg_no", cg_no);
		 * map.put("lowPrice", lowPrice); 
		 * map.put("highPrice", highPrice);
		 * map.put("color", color); 
		 * map.put("brand", brand); 
		 * map.put("score", score);
		 * map.put("sortBy", sortBy); 
		 * map.put("pageno", pageno);
		 * 
		 * map.put("start", pageMaker.getPageBegin()); 
		 * map.put("end", pageMaker.getPageEnd());
		 */
		/*
		 * -- sort 
		 * price_lh 
		 * price_hl 
		 * score
		 */
		System.out.println(searchMap);

		// 전체 상품 개수
		int productListCount = productDao.selectProductListCount(searchMap);
		System.out.println("상품개수:" + productListCount);
		// page계산
		int currentPage = Integer.parseInt(searchMap.get("pageno"));
		PageMaker pageMaker = new PageMaker(productListCount, currentPage, 9, 3);
		// 상품 리스트 얻기
		searchMap.put("start", pageMaker.getPageBegin() + "");
		searchMap.put("end", pageMaker.getPageEnd() + "");
		List<Product> productList = productDao.selectProductList(searchMap);
		PageMakerDto<Product> pageMakerProductList = new PageMakerDto<>(productList, pageMaker, productListCount);
		return pageMakerProductList;
	}

	/*
	 * 상품 상세정보 조회
	 */
	@Override
	public Product selectProductDetail(int p_no) throws Exception {
		productDao.updateProductClickCount(p_no);
		return productDao.selectProduct(p_no);
	}

	/*
	 * 인기상품, 추천상품 리스트 조회
	 */
	@Override
	public Map<String, List<Product>> selectMainProductList(String m_id) {
		Map<String, List<Product>> mainProductMap = new HashMap<>();
		List<String> defaultConceptList = new ArrayList<>(Arrays.asList(new String[] {"good","comfortable","special","fun"}));
		List<String> conceptList = null;
		String favoriteConcept = "";
		
		if (m_id != null) {
			// 회원의 선호 컨셉 조회
			conceptList = productDao.selectMemberBestProductConcept(m_id);
			// 선호컨셉을 defaultConceptList에서 삭제
			for (Iterator<String> iter = defaultConceptList.iterator(); iter.hasNext();) {
				  String concept = iter.next();
					if(conceptList.contains(concept)) {
							iter.remove();
					}
			}
		}
		
		// 인기상품 리스트 조회
		List<Product> popularList = productDao.selectMainProductList(null, 8);
		
		// 추천상품 리스트 조회
		List<Product> suggestionList = new ArrayList<>();
		// 상품리스트가 8개가 될때까지 List에 add
		for (int idx1 = 0, idx2 = 0; suggestionList.size() < 8;) {
			// 선호컨셉리스트를 먼저 조회
			if(conceptList != null && conceptList.size() > idx1) {
				favoriteConcept = conceptList.get(idx1++);
			// 선호컨셉을 모두 조회했다면 defaultConceptList를 조회
			} else if(defaultConceptList.size() > idx2) {
				favoriteConcept = defaultConceptList.get(idx2++);
			} else break;
			
			List<Product> productList = productDao.selectMainProductList(favoriteConcept, (8 - suggestionList.size()));
			for (Product product : productList) {
				suggestionList.add(product);
			}
		}
		mainProductMap.put("popularList",popularList);
		mainProductMap.put("suggestionList",suggestionList);
		return mainProductMap;
	}

}