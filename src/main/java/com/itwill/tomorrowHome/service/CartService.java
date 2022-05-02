package com.itwill.tomorrowHome.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.itwill.tomorrowHome.domain.Cart;



//2022.04.17. PM 05:45 권하나 작성
@Transactional
public interface CartService {


	int addInsert(int c_qty, int p_no, String m_id) throws Exception;

	int updateQty(int c_no, int c_qty) throws Exception;

	int updateQtyBynoAndid(int c_qty, int p_no, String m_id);

	int removeCartAll(String m_id) throws Exception;

	int removeCart(int c_no) throws Exception;

	boolean productExist(Cart cart) throws Exception;

	Cart getCartByNo(int c_no) throws Exception;

	List<Cart> cartListAll(String m_id) throws Exception;

}