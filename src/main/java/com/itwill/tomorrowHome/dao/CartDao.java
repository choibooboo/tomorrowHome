package com.itwill.tomorrowHome.dao;

import java.util.List;

import com.itwill.tomorrowHome.domain.Cart;

public interface CartDao {         
	/*    
	 * private int c_no;
		private int c_qty;
		private Product product;
		private String m_id;
	 */
	
	//카트에 새로운 항목을 추가
	int addInsert(int c_qty,int p_no,String m_id)throws Exception;
	int addInsert(Cart cart)throws Exception;
	
	//카트 수량 변경
	int updateQty(int c_no, int c_qty)throws Exception;
	int updateQty(Cart cart)throws Exception;
	
	//특정 회원의 특정 물품 갯수 변경
	int updateQtyBynoAndid(int c_no,int c_qty,String m_id);

	//카트 특정회원의 전체비우기
	int removeCartAll(String m_id)throws Exception;
	
	//카트 일부 비우기
	int removeCart(int c_no)throws Exception;
	
	//카트에 물건이 존재하나요?
	boolean productExist (Cart cart)throws Exception;
	boolean productExist (String m_id, int p_no)throws Exception;
	
	//카트아이템 1개 상세정보 선택
	Cart getCartByNo(int c_no)throws Exception;
	
	//멤버카트 리스트전체 출력
	List<Cart> cartListAll(String m_id) throws Exception;


}
