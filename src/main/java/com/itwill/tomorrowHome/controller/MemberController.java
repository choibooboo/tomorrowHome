package com.itwill.tomorrowHome.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.tomorrowHome.controller.interceptor.LoginCheck;
import com.itwill.tomorrowHome.domain.Cart;
import com.itwill.tomorrowHome.service.CartService;

@Controller
public class MemberController {
	@Autowired
	private CartService cartService;

	/*
	 * 로그인폼 
	 */
	@RequestMapping("/login_form")
	public String login_form() {
		return "login";
	}

	/*
	 * 마이페이지
	 */
	@LoginCheck
	@RequestMapping("/my_account")
	public String my_account(HttpSession session, Model model) {
		try {
			String sM_id = (String) session.getAttribute("sM_id");
			List<Cart> cartList = cartService.cartListAll(sM_id);
			model.addAttribute("cartList", cartList);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "my-account";
	}

	/*
	 * 회원 정보 보기
	 */
	@LoginCheck
	@RequestMapping("/account_details")
	public String account_details(HttpSession session, Model model) {
		try {
			String sM_id = (String) session.getAttribute("sM_id");
			List<Cart> cartList = cartService.cartListAll(sM_id);
			model.addAttribute("cartList", cartList);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "account-details";
	}

	/*
	 * 로그아웃
	 */
	@LoginCheck
	@RequestMapping("/member_logout")
	public String member_login_rest(HttpSession session) {
		session.invalidate();
		return "redirect:index";
	}

}
