package com.itwill.tomorrowHome.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.tomorrowHome.domain.Cart;
import com.itwill.tomorrowHome.domain.Product;
import com.itwill.tomorrowHome.service.CartService;
import com.itwill.tomorrowHome.service.ProductService;

@Controller
public class MainController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CartService cartService;

	/*
	 * 메인페이지
	 */
	@RequestMapping("/index")
	public String index(HttpSession session, Model model) {
		try {
			String sM_id = (String) session.getAttribute("sM_id");
			Map<String, List<Product>> mainProductList = productService.selectMainProductList(sM_id);
			model.addAttribute("popularList", mainProductList.get("popularList"));
			model.addAttribute("suggestionList", mainProductList.get("suggestionList"));
			if (sM_id != null) {
				List<Cart> cartList = cartService.cartListAll(sM_id);
				model.addAttribute("cartList", cartList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "index";
	}

	/*
	 * FAQ 페이지
	 */
	@RequestMapping("/faq")
	public String faq_page(HttpSession session, Model model) {
		try {
			String sM_id = (String) session.getAttribute("sM_id");
			if (sM_id != null) {
				List<Cart> cartList = cartService.cartListAll(sM_id);
				model.addAttribute("cartList", cartList);
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "faq";
	}
	
	/*
	 * session여부 체크
	 */
	@ResponseBody
	@RequestMapping("/check_session")
	public String check_session(HttpSession session) {
		String result = "N"; 
		if(session.getAttribute("sM_id") != null) {
			result = "Y";
		}
		return result;
	}
	
	/*
	 * 현재 날짜,시간정보 반환 
	 */
	@ResponseBody
	@RequestMapping("/get_now_time")
	public String get_now_time() {
		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
	}
	
}
