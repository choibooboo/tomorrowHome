package com.itwill.tomorrowHome.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.tomorrowHome.domain.Cart;
import com.itwill.tomorrowHome.domain.Image;
import com.itwill.tomorrowHome.domain.Product;
import com.itwill.tomorrowHome.service.CartService;
import com.itwill.tomorrowHome.service.ProductService;
import com.itwill.tomorrowHome.util.PageMakerDto;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CartService cartService;

	/*
	 * 상품 리스트 페이지
	 */
	@RequestMapping("/product_list")
	public String shop_grid(@RequestParam Map<String, String> params, Model model, HttpSession session) {
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
			model.addAttribute("productList", productList); 
			String sM_id = (String)session.getAttribute("sM_id");
			if (sM_id != null) {
				List<Cart> cartList = cartService.cartListAll(sM_id);
				model.addAttribute("cartList", cartList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "shop_grid_left_sidebar";
	}
	
	/*
	 * 상품상세보기
	 */
	@RequestMapping(value="/product_detail")
	public String product_detail(@RequestParam Integer p_no, Model model, HttpSession session) {
		if(p_no == null) {
			return "redirect:index";
		}
		String sM_id = (String)session.getAttribute("sM_id");
		try {
			Product product = productService.selectProductDetail(p_no);
			Map<String, List<Product>> productList = productService.selectMainProductList(sM_id);
			model.addAttribute("productList", productList.get("suggestionList"));
			model.addAttribute("product", product);
			List<Image> imgList = new ArrayList<>();
			List<Image> descImgList = new ArrayList<>();
			List<Image> sizeImgList = new ArrayList<>();
			for(Image image : product.getImageList()) {
				switch(image.getIm_div()) {
					case "IMG" : imgList.add(image); break;
					case "DESC" : descImgList.add(image); break;
					case "SIZE" : sizeImgList.add(image); break;
				}
			};
			model.addAttribute("imgList", imgList);
			model.addAttribute("descImgList", descImgList);
			model.addAttribute("sizeImgList", sizeImgList);
			if (sM_id != null) {
				List<Cart> cartList = cartService.cartListAll(sM_id);
				model.addAttribute("cartList", cartList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "product-detail";
	}
	
	/*
	 * Get Mapping
	 */
}
