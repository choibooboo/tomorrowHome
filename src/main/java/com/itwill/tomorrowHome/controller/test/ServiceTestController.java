package com.itwill.tomorrowHome.controller.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.tomorrowHome.domain.Member;
import com.itwill.tomorrowHome.domain.Product;
import com.itwill.tomorrowHome.domain.Qna;
import com.itwill.tomorrowHome.service.CartService;
import com.itwill.tomorrowHome.service.MemberService;
import com.itwill.tomorrowHome.service.OrderService;
import com.itwill.tomorrowHome.service.ProductService;
import com.itwill.tomorrowHome.service.QnaService;
import com.itwill.tomorrowHome.service.ReviewService;
import com.itwill.tomorrowHome.service.WishListService;
import com.itwill.tomorrowHome.util.PageMakerDto;

@Controller
public class ServiceTestController {
	@Autowired
	private ReviewService reviewService; 
	@Autowired
	private QnaService qnaService; 
	@Autowired
	private ProductService productService; 
	@Autowired
	private WishListService wishListService;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private MemberService memberService;
	/*
	 * Member
	 */
	@RequestMapping("/member_service_test") 
	public String member_service_test() throws Exception {
		System.out.println("member_service_test");
		System.out.println("멤버 생성>>>");
		//System.out.println(memberService.createMember(new Member("member5", "0000", "멤버5", "member5@naver.com", "멤버시 멤버구", "111-222", "010-1111-2222")));
		System.out.println("멤버 정보 수정>>>");
		//System.out.println(memberService.updateMember(new Member("member5", "6666", "멤버6", "member6@naver.com", "서울시 강남구", "222-111", "010-2222-1111")));
		System.out.println("멤버 비밀번호 변경>>>");
		//System.out.println(memberService.updateMemberPassword(new Member("member5", "7777", null, null, null, null, null)));
		System.out.println("멤버 삭제>>>");
		//System.out.println(memberService.deleteMember("member5"));
		System.out.println("멤버 조회>>>");
		//System.out.println(memberService.findMember("home3"));
		System.out.println("회원 아이디 존재 확인>>>");
		//System.out.println(memberService.existedMember("home3"));
		System.out.println("회원 로그인>>>");
		System.out.println(memberService.loginMember("home3", "1472"));
		return "test/test_result";
	}
	
	/*
	 * Cart 
	 */
	@RequestMapping("/cart_service_test") 
	public String cart_service_test() throws Exception {
		System.out.println("cart_service_test");
		cartService.addInsert(1, 1, "home3");
		//System.out.println(cartService.getCartByNo(1));
		//System.out.println(cartService.removeCart(1));
		//System.out.println(cartService.removeCartAll("home2"));
		
		return "test/test_result";
	}
	
	/*
	 * WishList
	 */
	@RequestMapping("/wishlist_service_test") 
	public String wishlist_service_test() throws Exception {
		System.out.println("wishlist_service_test");
		System.out.println(wishListService.insertWish("home3", 1));
		//System.out.println(wishListService.removeWishAll("home1"));
		//System.out.println(wishListService.removeWishByNo(1));
		//System.out.println(wishListService.getWishByNo(1));
		/*
		for (WishList wishListService : wishListService.wishListAll("home2")) {
			System.out.println(wishListService);
		}
		*/
		
		
		return "test/test_result";
	}
	
	/*
	 * Order
	 */
	@RequestMapping("/order_service_test") 
	public String order_service_test() throws Exception {
		System.out.println("order_service_test");
		/*
		 주문 전체 삭제
		System.out.println(orderService.delete("toto"));
		 */
		
		/*
		 * 주문 1개 삭제
		System.out.println(orderService.deleteByOrderNo(37));
		 */
		/*
		 * 상품에서 직접 주문
		System.out.println(orderService.create("toto", 1, 3, "card", "결제완료", "toto", "010-4245-2952", "강동구", "05307", "안전하게보내주세요", "toto"));
		 */
		/*
		 * 주문목록
		System.out.println(orderService.list("toto"));
		 */
		/*
		 * 주문상세보기
		System.out.println(orderService.detail(42));
		 */
		/*
		 * cart에서 주문(전체 선택 주문)
		System.out.println(orderService.create("home1", "card", "결제중", "home1", "010-8529-4852", "동작구", "05320", "문 앞에 놔주세요"));
		 */
		
		/*
		 * cart에서 선택주문
		 */
		/*
		String sUserId, int[] cart_item_no_array,String o_pay_method, String o_status,
			String o_rv_name, String o_rv_phone, String o_rv_address, String o_rv_post, String o_message
		int[] cart_no = new int[] {7,8};
		System.out.println(orderService.create("toto", cart_no , "card", "결제완료", "aaaaaaa", "010-4256-2345", "서울시", "05043", "빠른배송해주세요"));
		
		 */
		return "test/test_result";
	}
	
	/*
	 * Review
	 */
	@RequestMapping("/review_service_test") 
	public String review_service_test() throws Exception {
		System.out.println("review_service_test");
		
		System.out.println("특정리뷰정보>>"); 
		System.out.println(reviewService.selectProductReview(1)); 
		/*
		System.out.println("리뷰등록>>"); 
		Review r = new Review(); 
		r.setR_content("맘에듭니다"); 
		r.setR_title("제목임"); 
		r.setR_score(4); 
		r.setM_id("home1"); 
		r.setP_no(11); 
		System.out.println(reviewService.insertProductReview(r));   
		System.out.println("리뷰수정>>");
		r.setR_no(3);
		r.setR_content("수정함"); 
		System.out.println(reviewService.updateReview(r));  
		System.out.println("리뷰삭제>>");
		System.out.println(reviewService.deleteReview(1));   
		*/

		return "test/test_result";
	} 
	
	/*
	 * Qna
	 */
	@RequestMapping("/qna_service_test") 
	public String qna_service_test() throws Exception {
		System.out.println("qna_service_test");
		
		System.out.println("게시물하나정보>>>>");
		System.out.println(qnaService.selectQna(1, "view"));
		System.out.println("게시물리스트>>>>");
		PageMakerDto<Qna> pmq = qnaService.selectQnaList(1);
		for(Qna qna : pmq.getItemList()){
			System.out.println(qna); 
		}
		System.out.println("전체게시물개수>>>>"); 
		System.out.println(qnaService.selectQnaCount()); 
		System.out.println("게시물조회수변경>>>"); 
		qnaService.updateReadCount(1);
		/*
		System.out.println("게시물등록>>>>");
		Qna qna1 = new Qna();
		qna1.setQ_title("게시글제목aaaa");
		qna1.setQ_content("게시글내용aaaa");
		qna1.setM_id("toto");
		System.out.println(qnaService.insertNewQna(qna1));
		System.out.println("답글등록>>>>");
		Qna qna2 = new Qna();
		qna2.setQ_no(2); // 답글의 원글 번호
		qna2.setQ_title("답글제목aaaa"); // 답글의 제목
		qna2.setQ_content("답글내용aaaa"); // 답글의 내용
		qna2.setM_id("home1");
		System.out.println(qnaService.insertReply(qna2));
		System.out.println("게시물수정aaa>>>");
		Qna qna3 = new Qna();
		qna3.setQ_title("제목수정aa");
		qna3.setQ_content("내용수정aa");
		qna3.setQ_no(1);
		System.out.println(qnaService.updateQna(qna3));  
		System.out.println("게시물삭제>>>");
		System.out.println(qnaService.deleteQna(7));
		*/
		return "test/test_result";
	}

	/*
	 * Product
	 */
	@RequestMapping("/product_service_test") 
	public String product_service_test() throws Exception {
		System.out.println("product_service_test");
		
		System.out.println("상품리스트 조회>>>>>");
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("cg_no", null); 
		searchMap.put("lowPrice", null); 
		searchMap.put("highPrice", null); 
		searchMap.put("color", "red");
		searchMap.put("brand", null);
		searchMap.put("score", null);
		searchMap.put("sortBy", "score");
		searchMap.put("pageno", "1");
		PageMakerDto<Product> pageMakerProductList = productService.selectProductList(searchMap);
		for(Product p : pageMakerProductList.getItemList()) {
			System.out.println(p);
		}
		System.out.println("상품상세정보 조회>>>>>");
		System.out.println(productService.selectProductDetail(3));
		System.out.println("인기상품, 추천상품 리스트 조회>>>>");
		Map<String, List<Product>> mainMap = productService.selectMainProductList("toto");
		System.out.println("1 ===> 인기상품");
		for(Product p : mainMap.get("popularList")) {
			System.out.println(p);
		} 
		System.out.println("2 ===> 추천상품");
		for(Product p : mainMap.get("suggestionList")) {
			System.out.println(p);
		}
		
		return "test/test_result";
	}
	
}
