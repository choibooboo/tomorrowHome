package com.itwill.tomorrowHome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.tomorrowHome.dao.CartDao;
import com.itwill.tomorrowHome.dao.CartDaoImpl;
import com.itwill.tomorrowHome.dao.ProductDao;
import com.itwill.tomorrowHome.domain.Cart;
import com.itwill.tomorrowHome.domain.Product;

//2022.04.17. PM 06:10 권하나 수정
@Service
public class CartServiceImpl implements CartService {
		@Autowired
		private CartDao cartDao;
		public CartServiceImpl() {
		cartDao = new CartDaoImpl();
	}
	@Override
	public int addInsert(int c_qty,int p_no,String m_id) throws Exception {
		if (cartDao.productExist(m_id, p_no)) {
			return cartDao.updateQtyBynoAndid(c_qty,p_no, m_id);
		}else {
			return cartDao.addInsert(c_qty, p_no, m_id);
		}
		
		
		
	}
	/* 2022.04.17. PM 9:25 권하나 작성중
	public int addInsert(int c_qty,int p_no,String m_id) throws Exception {
		if(cartDao.productExist(Cart cart)) {
			return cartDao.updateQty(p_no, c_qty);
		}else {
			return cartDao.addInsert(c_qty, p_no, m_id);
		}
	}
	 */
	
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
