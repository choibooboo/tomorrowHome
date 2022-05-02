package com.itwill.tomorrowHome.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.tomorrowHome.domain.Product;
import com.itwill.tomorrowHome.service.ProductService;
import com.itwill.tomorrowHome.util.PageMakerDto;

@RestController
public class ProductRestController {
	@Autowired
	private ProductService productService;

	/*
	 * 상품 리스트 페이지(REST)
	 */
	@RequestMapping("/product_list_rest")
	public Map<String, Object> product_list_rest(@RequestParam Map<String, String> params) {
		Map<String, Object> resultMap = new HashMap<>();	
		PageMakerDto<Product> productList = null;
		try {
			System.out.println(params);
			/*
			("cg_no");
			("lowPrice");
			("highPrice");
			("color");
			("brand");
			("score");
			("sortBy");
			("search");
			(pageno)
			*/
			productList = productService.selectProductList(params); 
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "성공");
			resultMap.put("data", productList);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}
	
	/*
	 * 상품 상세(REST)
	 */
	@RequestMapping(value="/product_detail_rest", produces = "application/json;charset=utf-8")
	public Map<String, Object> product_detail(@RequestParam Integer p_no) {
		Map<String, Object> resultMap = new HashMap<>();
		if(p_no == null) {
			resultMap.put("errorCode", "-1");
			resultMap.put("errorMsg", "잘못된 접근입니다");
			return resultMap;
		}
		try {
			Product product = productService.selectProductDetail(p_no);
			resultMap.put("errorCode", "1");
			resultMap.put("errorMsg", "성공");
			resultMap.put("data", product);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", "-2");
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}
	
	/*
	 * Get Mapping
	 */
}
