package com.itwill.tomorrowHome.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.tomorrowHome.controller.interceptor.LoginCheck;
import com.itwill.tomorrowHome.domain.Order;
import com.itwill.tomorrowHome.service.OrderService;

@RestController
public class OrderRestController {
	@Autowired
	private OrderService orderService;

	/*
	 * 주문상세 
	 */
	@LoginCheck
	@RequestMapping(value = "order_detail_rest", produces = "application/json;charset=UTF-8")
	public Map<String, Object> order_detail_rest(@RequestParam Integer o_no) {
		Map<String, Object> resultMap = new HashMap<>();
		if (o_no == null) {
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "잘못된 접근입니다");
			return resultMap;
		}
		try {
			Order order = orderService.detail(o_no);
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "성공");
			resultMap.put("data", order);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}

	/*
	 * 주문 1개 삭제
	 */
	@LoginCheck
	@PostMapping("order_item_delete_action_rest")
	public Map<String, Object> order_item_delete_action_rest(@RequestParam Integer o_no) {
		Map<String, Object> resultMap = new HashMap<>();
		if (o_no == null) {
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "잘못된 접근입니다");
			return resultMap;
		}
		try {
			orderService.deleteByOrderNo(o_no);
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "주문 내역이 삭제되었습니다");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -2);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}

	/*
	 * 주문전체삭제
	 */
	@LoginCheck
	@PostMapping("order_all_delete_action_rest")
	public Map<String, Object> order_all_delete_action_rest(HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			String sM_id = (String) session.getAttribute("sM_id");
			orderService.delete(sM_id);
			resultMap.put("errorCode", 1);
			resultMap.put("errorMsg", "전체 주문 내역이 삭제되었습니다");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorCode", -1);
			resultMap.put("errorMsg", "관리자에게 문의하세요");
		}
		return resultMap;
	}
}
