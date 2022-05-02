package com.itwill.tomorrowHome.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.tomorrowHome.dao.CartDao;
import com.itwill.tomorrowHome.dao.MemberDao;
import com.itwill.tomorrowHome.dao.OrderDao;
import com.itwill.tomorrowHome.dao.ProductDao;
import com.itwill.tomorrowHome.dao.QnaDao;
import com.itwill.tomorrowHome.dao.ReviewDao;
import com.itwill.tomorrowHome.dao.WishListDao;
import com.itwill.tomorrowHome.domain.Cart;
import com.itwill.tomorrowHome.domain.Order;
import com.itwill.tomorrowHome.domain.OrderItem;
import com.itwill.tomorrowHome.domain.Product;
import com.itwill.tomorrowHome.domain.Review;

@Controller
public class DaoTestController {   
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private QnaDao qnaDao;
	@Autowired
	private CartDao cartDao; 
	@Autowired
	private WishListDao wishListDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private MemberDao memberDao;
	/*
	 * Member
	 */
	@RequestMapping("/member_dao_test") 
	public String member_dao_test() throws Exception {
		System.out.println("member_dao_test");
		System.out.println("멤버 생성>>");
		//System.out.println(memberDao.createMember(new Member("guard1", "xxxx", "가드", "guard@naver.com", "시시 구구", "999-999", "010-9999-9999")));
		System.out.println("멤버 정보 수정>>");
		//System.out.println(memberDao.updateMember(new Member("guard1", "yyyy", "가드2", "guard2@naver.com", "시시시 구구구", "888-888", "010-8888-8888")));
		System.out.println("멤버 비밀번호 변경>>");
		//System.out.println(memberDao.updateMemberPassword(new Member("guard1", "zzzz", null, null, null, null, null)));
		System.out.println("멤버 삭제>>");
		//System.out.println(memberDao.deleteMember("guard1"));
		System.out.println("멤버 조회>>");
		//System.out.println(memberDao.findMember("home2"));
		System.out.println("회원 아이디 존재확인>>");
		//System.out.println(memberDao.existedMember("home2")); 
		return "test/test_result";
	}
	
	/*
	 * Cart 
	 */
	@RequestMapping("/cart_dao_test") 
	public String cart_dao_test() throws Exception {
		System.out.println("cart_dao_test");
		//카트추가
		//cartDao.addInsert(1, 1, "home1");
		
		//cartDao.updateQtyBynoAndid(1, 1, "home1");
		
		//cartDao.removeCartAll("home1");
		
		//cartDao.removeCart(3);
		
		Cart cart = new Cart(0, 
							 1, 
							new Product(1, null, 0, null, null, 0, null, null, 0, null, 0, null, null),
							"home1");
							 
		System.out.println(cartDao.productExist(cart));
		
		//System.out.println(cartDao.getCartByNo(2));
		
		//System.out.println(cartDao.updateQty(1, 1));
		//for (Cart CartList : cartDao.cartListAll("home1")) {
		//System.out.println(CartList);
		//}
		
		return "test/test_result";
	}

	/*
	 * WishList
	 */
	@RequestMapping("/wishlist_dao_test") 
	public String wishlist_dao_test() throws Exception {
		System.out.println("wishlist_dao_test");
		System.out.println(wishListDao.insertWish("home3", 1));
		//System.out.println(wishListDao.removeWishByNo(1));
		//System.out.println(wishListDao.productWishExist("home1", 1));
		
		/*
		WishList wishList = new WishList(0,
										"home3",
										new Product(3, null, 0, null, null, 0, null, null, 0, null, 0, null, null));
		System.out.println(wishListDao.productWishExist(wishList));
		*/
		/*
		System.out.println(wishListDao.getWishByNo(4));
		for (WishList wishList : wishListDao.wishListAll("home3")) {
			System.out.println(wishList);
		}
		*/
		return "test/test_result";
	}
	
	/*
	 * Order
	 */
	@RequestMapping("/order_dao_test") 
	public String order_dao_test() throws Exception {
		System.out.println("order_dao_test");
		
		System.out.println("멤버의 주문리스트>>>");
		System.out.println(orderDao.list("toto"));
		System.out.println("주문상세>>>");
		System.out.println(orderDao.detail(11));
		
		System.out.println("주문생성>>>>");
		// 주문아이템리스트 세팅  
		ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
		OrderItem orderItem1 = new OrderItem();
		Product p = new Product();
		p.setP_no(10);
		orderItem1.setOi_qty(2);
		orderItem1.setProduct(p);
		orderItemList.add(orderItem1);
		// 주문 객체 생성 
		Order order1 = new Order();
		order1.setO_desc("주문명이당");
		order1.setO_price(1000000);
		order1.setO_pay_method("CARD");
		order1.setO_status("결제완료");
		order1.setO_rv_name("너구리");
		order1.setO_rv_phone("010-1111-2222");
		order1.setO_rv_address("주소입니당당");
		order1.setO_rv_post("02030");
		order1.setO_message("빠른배송 부탁드려요");
		order1.setM_id("home1");
		order1.setOrderItemList(orderItemList);
		//System.out.println(orderDao.create(order1));
		
		System.out.println("주문삭제>>>");
		//System.out.println(orderDao.delete("toto"));
		//System.out.println(orderDao.deleteByOrderNo(10));
		System.out.println("멤버의 특정 상품 주문 여부 조회>>");

		System.out.println(orderDao.selectMemberProductOrderCount("toto", 10));
		
		return "test/test_result";
	}
	
	/*
	 * Review
	 */
	@RequestMapping("/review_dao_test") 
	public String reviewTest() throws Exception {
		System.out.println("review_dao_test");
		System.out.println("상품의리뷰리스트>>");  
		for(Review r : reviewDao.selectProductReviewList(6)) {
			System.out.println(r);  
		}
		System.out.println("특정리뷰정보>>");   
		System.out.println(reviewDao.selectProductReview(1));
		/*
		System.out.println("리뷰등록>>");  
		Review r = new Review();
		r.setR_content("맘에듭니다");  
		r.setR_title("제목임");  
		r.setR_score(4);
		r.setM_id("toto");
		r.setP_no(10);
		System.out.println(reviewDao.insertProductReview(r));   
		System.out.println("회원의특정상품리뷰작성여부>>");
		System.out.println(reviewDao.selectMemberProductReviewCount("toto", 10)); 
		System.out.println("리뷰수정>>");
		r.setR_no(3);
		r.setR_content("수정함"); 
		System.out.println(reviewDao.updateReview(r));  
		System.out.println("리뷰삭제>>");
		System.out.println(reviewDao.deleteReview(1));   
		System.out.println("리뷰평균>>"); 
		System.out.println(reviewDao.selectProductReviewAvgScore(6));   
		*/
		return "test/test_result";
	} 
	
	/*
	 * Qna
	 */
	@RequestMapping("/qna_dao_test") 
	public String qna_test() throws Exception {
		System.out.println("게시물하나정보>>>>");
		System.out.println(qnaDao.selectQna(1));
		System.out.println("게시물리스트>>>>");
		System.out.println(qnaDao.selectQnaList(1, 10));
		System.out.println("전체게시물개수>>>>");
		System.out.println(qnaDao.selectQnaCount());
		/*
		System.out.println("게시물등록>>>>");
		Qna qna1 = new Qna();
		qna1.setQ_title("게시글제목");
		qna1.setQ_content("게시글내용");
		qna1.setM_id("toto");
		System.out.println(qnaDao.insertNewQna(qna1));
		System.out.println("답글등록>>>>");
		Qna qna2 = new Qna();
		qna2.setQ_no(2); // 답글의 원글 번호
		qna2.setQ_title("답글제목"); // 답글의 제목
		qna2.setQ_content("답글내용"); // 답글의 내용
		qna2.setM_id("home1");
		System.out.println(qnaDao.insertReply(qna2));
		System.out.println("게시물수정>>>");
		Qna qna3 = new Qna();
		qna3.setQ_title("제목수정");
		qna3.setQ_content("내용수정");
		qna3.setQ_no(1);
		System.out.println(qnaDao.updateQna(qna3));  
		System.out.println("게시물삭제>>>");
		System.out.println(qnaDao.deleteQna(4));
		 */ 
		System.out.println("게시물조회수변경>>>");
		qnaDao.updateReadCount(1);  
		return "test/test_result";
	}

	/*
	 * Product
	 */
	@RequestMapping("/product_dao_test") 
	public String product_dao_test() throws Exception {
		System.out.println("product_dao_test");
		/*
		Map<String, String> map = new HashMap<>();
		map.put("cg_no", cg_no);
		map.put("lowPrice", lowPrice); 
		map.put("highPrice", highPrice);
		map.put("color", color);
		map.put("brand", brand);
		map.put("score", score);
		map.put("sortBy", sortBy);
		 * -- sort
		 * price_lh
		 * price_hl
		 * score
		*/
		/*-- concept
		 * special
		 * fun
		 * good
		 * comfortable
		 */
		Map<String, String> map = new HashMap<>();
		map.put("cg_no", null); 
		map.put("lowPrice", "0"); 
		map.put("highPrice", "60000"); 
		map.put("color", null); 
		map.put("brand", null); 
		map.put("score", null); 
		map.put("sortBy", "price_lh"); 
		 
		System.out.println("멤버가 가장 선호하는 상품 컨셉 조회>>>"); 
		List<String> list = productDao.selectMemberBestProductConcept("home2");
		for(String c : list ) {
			System.out.println(c);
		}
		System.out.println("상품 평균 평점 수정>>>");
		System.out.println(productDao.updateProductAvgScore(3.5, 10));
		System.out.println("상품 전체 개수 조회>>>");
		System.out.println(productDao.selectProductListCount(new HashMap<String,String>()));
		System.out.println(productDao.selectProductListCount(map));
		System.out.println("상품 전체 리스트 조회>>>");
		map.put("start", "1");
		map.put("end", "9");
		for(Product p : productDao.selectProductList(map)) {
			System.out.println(p);
		}
		System.out.println("상품 상세정보 조회>>>");
		System.out.println(productDao.selectProduct(3));
		System.out.println("인기상품 리스트 조회>>>");
		List<Product> plist = productDao.selectMainProductList(null, 8);
		for(Product p : plist) {
			System.out.println("SIZE ===> " + plist.size());
			System.out.println(p);
		}
		System.out.println("추천상품 리스트 조회>>>");
		for(Product p : productDao.selectMainProductList("fun", 8)) {
			System.out.println(p);
		}
		return "test/test_result";
	}
	
}
