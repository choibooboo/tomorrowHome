package com.itwill.tomorrowHome.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.tomorrowHome.controller.interceptor.LoginCheck;
import com.itwill.tomorrowHome.domain.Cart;
import com.itwill.tomorrowHome.domain.Order;
import com.itwill.tomorrowHome.domain.Product;
import com.itwill.tomorrowHome.service.CartService;
import com.itwill.tomorrowHome.service.OrderService;
import com.itwill.tomorrowHome.service.ProductService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService; 
	@Autowired
	private ProductService productService; 
	@Autowired
	private CartService cartService;   
	
	/*
	 * <<< 주문 정보 session 저장 >>> 
	 * - 주문시 카트 번호를 전달하기 위한 array
	 * session.setAttribute("cart_item_noStr_array", cart_item_noStr_array); 
	 * - 주문form에 상품정보 출력을 위한 list 
	 * session.setAttribute("cartItemList", cartItemList);
	 * - 주문 정보를 저장하는 Order 객체 
	 * session.setAttribute("order", order);
	 */
	/*
	 * 주문 상품 정보 저장 
	 * 주문자 정보로 이동
	 */
	@LoginCheck
	@PostMapping("/checkout_2")
	public String order_orderer_form(HttpSession session, String buyType, String p_no, String qty,
			@RequestParam(value = "cart_no", required = false) String[] cart_item_noStr_array, Model model) {

		String sM_id = (String) session.getAttribute("sM_id");
		// 주문할 상품리스트를 담을 cartItemList. 주문 form에 상품정보 출력을 위한 list다.
		List<Cart> cartItemList = new ArrayList<>();
		try {
			List<Cart> cartList = cartService.cartListAll(sM_id); 
			if (buyType.equals("cart_select")) {
				// 장바구니 선택 주문의 경우, 전달된 cart번호로 cart객체를 가져와 리스트에 세팅
				for (String cart_item_noStr : cart_item_noStr_array) {
					cartItemList.add(cartService.getCartByNo(Integer.parseInt(cart_item_noStr)));
				}
				// 주문시 카트 번호를 전달하기 위한 array
				session.setAttribute("cart_item_noStr_array", cart_item_noStr_array);
				
			} else if (buyType.equals("direct")) {
				// 바로 주문의 경우 상품번호로 상품객체를 가져와 상품객체와 수량을 카트객체에 세팅 후, 카트객체를 리스트에 세팅
				Product product = productService.selectProductDetail(Integer.parseInt(p_no));
				cartItemList.add(new Cart(0, Integer.parseInt(qty), product, sM_id));
				session.setAttribute("p_no", p_no);
				session.setAttribute("qty", qty);
				
			} else if (buyType.equals("cart")) {
				cartItemList = cartList;
			}
			session.setAttribute("buyType", buyType);
			session.setAttribute("cartItemList", cartItemList);
			model.addAttribute("cartList", cartList);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "checkout-2";
	}

	/*
	 * 주문자 정보 저장 
	 * 배송 정보 이동
	 */
	@LoginCheck
	@PostMapping("/checkout_3")
	public String order_reciever_form(HttpSession session, @RequestParam Map<String, String> params, Model model) {
		try {
			String sM_id = (String) session.getAttribute("sM_id");
			Order order = new Order();
			order.setM_id(sM_id);
			session.setAttribute("order", order);
			model.addAttribute("cartList", cartService.cartListAll(sM_id));
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "checkout-3";
	}

	/*
	 * 배송 정보 저장 결제 정보로 이동
	 */
	@LoginCheck
	@PostMapping("/checkout_4")
	public String order_payment_form(HttpSession session, @RequestParam Map<String, String> params, Model model) {
		try {
			Order order = (Order) session.getAttribute("order");
			order.setO_rv_name(params.get("o_rv_name"));
			order.setO_rv_phone(params.get("o_rv_phone"));
			order.setO_rv_address(params.get("o_rv_address"));
			order.setO_rv_post(params.get("o_rv_post"));
			order.setO_message(params.get("o_message"));
			model.addAttribute("cartList", cartService.cartListAll(order.getM_id()));
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "checkout-4";
	}

	/*
	 * 결제 방법 정보 저장 주문 정보 확인창으로 이동
	 */
	@LoginCheck
	@PostMapping("/checkout_5")
	public String order_confirm_form(HttpSession session,
			@RequestParam String o_pay_method, Model model) {
		try {
			Order order = (Order) session.getAttribute("order");
			order.setO_pay_method(o_pay_method);
			switch (o_pay_method) {
			case "CARD":
			case "PayPal":
				order.setO_status("결제완료"); break;
			case "BankTransfer":
			case "CoD":
				order.setO_status("결제대기"); break;
			}
			model.addAttribute("cartList", cartService.cartListAll(order.getM_id()));
			System.out.println(session.getAttribute("cartItemList"));
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "checkout-5";
	}

	/*
	 * 주문 생성
	 */
	@LoginCheck
	@PostMapping("/create_order")
	public String create_order(HttpSession session, Model model, String total_price) {
		try {
			Order order = (Order) session.getAttribute("order");
			order.setO_price(Integer.parseInt(total_price));
			String buyType = (String)session.getAttribute("buyType");
			if(buyType.equals("cart")) {
				orderService.create(order); 
			}else if(buyType.equals("cart_select")) {
				orderService.create((String[])session.getAttribute("cart_item_noStr_array"), order);
			}else if(buyType.equals("direct")) {
				int p_no = Integer.parseInt((String) session.getAttribute("p_no"));
				int oi_qty = Integer.parseInt((String) session.getAttribute("qty"));
				orderService.create(p_no, oi_qty, order); 
			}
			model.addAttribute("status", order.getO_status());
			model.addAttribute("cartList", cartService.cartListAll(order.getM_id()));
			session.removeAttribute("order");
			session.removeAttribute("p_no");
			session.removeAttribute("oi_qty");
			session.removeAttribute("buyType");
			session.removeAttribute("cartItemList");
			session.removeAttribute("cart_item_noStr_array");
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "checkout-complate";
	}

	/*
	 * 주문리스트
	 */
	@LoginCheck
	@RequestMapping("/order_list")
	public String order_list(HttpSession session, Model model) {
		try {
			String sM_id = (String) session.getAttribute("sM_id");
			List<Cart> cartList = cartService.cartListAll(sM_id);
			List<Order> orderList = orderService.list(sM_id);
			model.addAttribute("cartList", cartList);
			model.addAttribute("orderList", orderList); 
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "order-list";
	}
	
	/*
	 * Get Mapping
	 */
	@GetMapping({ "/checkout_2", "/checkout_3", "/checkout_4", "/checkout_5", "/create_order" })
	public String get_mapping() {
		return "redirect:index";
	}
}
