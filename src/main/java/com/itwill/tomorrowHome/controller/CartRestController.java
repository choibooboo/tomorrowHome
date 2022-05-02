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
import com.itwill.tomorrowHome.service.CartService;

@RestController
public class CartRestController {
	@Autowired
	private CartService cartService;
	
	/*
	 * 카트 아이템 삭제
	 */
	@LoginCheck
	@RequestMapping(value = "/cart_remove_rest", produces = "application/json;charset=UTF-8")
	public Map<String, Object> cart_remove_rest(@RequestParam List<String> param, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		if(param == null) {
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "잘못된 접근입니다");
			return resultMap;
		}
		try {
			for(String c_no : param) {
				cartService.removeCart(Integer.parseInt(c_no));
			}
			String sM_id = (String)session.getAttribute("sM_id");
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "상품이 카트에서 삭제되었습니다");
			resultMap.put("data", cartService.cartListAll(sM_id));
			if(param.size() == 1) {
				resultMap.put("cart", cartService.getCartByNo(Integer.parseInt(param.get(0))));
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}
	
	/*
	 * 카트 수량 수정
	 */
	@LoginCheck
	@RequestMapping(value = "cart_update_action_rest",produces = "application/json;charset=UTF-8")
	public Map<String, Object> cart_update_action_rest(@RequestParam Integer c_no,@RequestParam Integer c_qty,HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> dataMap = new HashMap<>();
		if(c_no == null || c_qty == null) {
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "잘못된 접근입니다");
			return resultMap;
		}
		try {
			cartService.updateQty(c_no, c_qty);
			String sM_id = (String)session.getAttribute("sM_id");
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "수량을 변경하였습니다");
			dataMap.put("cartList", cartService.cartListAll(sM_id));
			dataMap.put("cart", cartService.getCartByNo(c_no));
			resultMap.put("data", dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}
	
	/*
	 * 카트 추가 
	 */
	@LoginCheck
	@RequestMapping("/add_cart_rest")
	public Map<String,Object> add_cart_rest(HttpSession session, @RequestParam("p_no") String[] p_no_arr, @RequestParam Integer qty) {
		Map<String,Object> resultMap = new HashMap<>();
		if(p_no_arr == null || qty == null) {
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "잘못된 접근입니다");
			return resultMap;
		}
		try {
			String sM_id = (String)session.getAttribute("sM_id");
			for(String p_no : p_no_arr) {
				cartService.addInsert(qty, Integer.parseInt(p_no), sM_id);
			}
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "카트에 상품이 추가되었습니다");
			resultMap.put("data", cartService.cartListAll(sM_id));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}
	
}
