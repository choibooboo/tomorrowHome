package com.itwill.tomorrowHome.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.tomorrowHome.controller.interceptor.LoginCheck;
import com.itwill.tomorrowHome.service.WishListService;

@RestController
public class WishListRestController {
	@Autowired
	private WishListService wishListService;

	/*
	 * 위시리스트 추가
	 */
	@LoginCheck
	@RequestMapping("/add_wishList_rest")
	public Map<String, Object> add_wishList_rest(HttpSession session, @RequestParam Integer p_no) {
		Map<String, Object> resultMap = new HashMap<>();
		if (p_no == null) {
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "잘못된 접근입니다");
			return resultMap;
		}
		try {
			String sM_id = (String) session.getAttribute("sM_id");
			int result = wishListService.insertWish(sM_id, p_no);
			if (result != 0) {
				resultMap.put("errorCode", 1);
				resultMap.put("errorMsg", "위시리스트에 상품이 추가되었습니다");
				resultMap.put("data", wishListService.wishListAll(sM_id));
			} else {
				resultMap.put("errorCode", -2);
				resultMap.put("errorMsg", "위시리스트에 상품이 이미 존재합니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -3);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}
	
	/*
	 * 위시리스트 삭제
	 */
	@LoginCheck
	@RequestMapping("/delete_wishList_rest")
	public Map<String, Object> delete_wishList_rest(@RequestParam List<String> param) {
		Map<String, Object> resultMap = new HashMap<>();
		if(param == null) {
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "잘못된 접근입니다");
			return resultMap;
		}
		try {
			for(String w_no : param) {
				wishListService.removeWishByNo(Integer.parseInt(w_no));
			}
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "상품이 위시리스트에서 삭제되었습니다");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}

}
