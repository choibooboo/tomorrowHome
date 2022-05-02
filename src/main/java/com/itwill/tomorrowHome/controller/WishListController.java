package com.itwill.tomorrowHome.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.tomorrowHome.controller.interceptor.LoginCheck;
import com.itwill.tomorrowHome.domain.Cart;
import com.itwill.tomorrowHome.domain.WishList;
import com.itwill.tomorrowHome.service.CartService;
import com.itwill.tomorrowHome.service.WishListService;

@Controller
public class WishListController {
	@Autowired
	private WishListService wishListService;
	@Autowired
	private CartService cartService;

	/*
	 위시리스트 출력 
	 */
	@LoginCheck
	@RequestMapping("/wishlist_view")
	public String wishlist_view(HttpSession session, Model model) {
		try {
			String sM_id = (String) session.getAttribute("sM_id");
			List<WishList> wishListList = wishListService.wishListAll(sM_id);
			List<Cart> cartList = cartService.cartListAll(sM_id);
			model.addAttribute("wishListList", wishListList);
			model.addAttribute("cartList", cartList);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "wishlist";
	}

}
