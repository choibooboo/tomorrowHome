package com.itwill.tomorrowHome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.tomorrowHome.dao.CartDao;
import com.itwill.tomorrowHome.domain.Cart;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDao cartDao;

	@Override
	public int addInsert(int c_qty,int p_no,String m_id) throws Exception {
		if (cartDao.productExist(m_id, p_no)) {
			return cartDao.updateQtyBynoAndid(c_qty, p_no, m_id);
		}else {
			return cartDao.addInsert(c_qty, p_no, m_id);
		}
	}
	
	@Override
	public int updateQty(int c_no, int c_qty) throws Exception {
		return cartDao.updateQty(c_no, c_qty);
	}
	
	@Override
	public int updateQtyBynoAndid(int c_qty, int p_no, String m_id) {
		return cartDao.updateQtyBynoAndid(p_no, c_qty, m_id);
	}
	
	@Override
	public int removeCartAll(String m_id) throws Exception {
		return cartDao.removeCartAll(m_id);
	}
	
	@Override
	public int removeCart(int c_no) throws Exception {
		return cartDao.removeCart(c_no);
	}
	
	@Override
	public boolean productExist(Cart cart) throws Exception {
		return cartDao.productExist(cart);
	}
	
	@Override
	public Cart getCartByNo(int c_no) throws Exception {
		return cartDao.getCartByNo(c_no);
	}
	
	@Override
	public List<Cart> cartListAll(String m_id) throws Exception{
		return cartDao.cartListAll(m_id);
	}
}
