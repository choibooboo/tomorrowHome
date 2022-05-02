package com.itwill.tomorrowHome.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.tomorrowHome.controller.interceptor.LoginCheck;
import com.itwill.tomorrowHome.domain.Cart;
import com.itwill.tomorrowHome.service.CartService;

@Controller
public class CartController {
	@Autowired
	private CartService cartService;

	/*
	 * 카트 리스트
	 */
	@LoginCheck
	@RequestMapping("/cart_view")
	public String cart_view(HttpServletRequest request, HttpSession session) {
		String sM_id = (String) session.getAttribute("sM_id");
		try {
			List<Cart> cartList = cartService.cartListAll(sM_id);
			request.setAttribute("cartList", cartList);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

		return "cart";
	}
	
}
