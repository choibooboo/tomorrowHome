package com.itwill.tomorrowHome.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.tomorrowHome.domain.Cart;
import com.itwill.tomorrowHome.domain.Product;

@Repository
public class CartDaoImpl implements CartDao{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int addInsert(int c_qty,int p_no,String m_id) throws Exception {
		Cart cart = new Cart(0,
							c_qty,
							new Product(p_no, null, 0, null, null, 0, null, null, 0, null, 0, null, null),
							m_id);
		return sqlSession.insert("mapper.cartMapper.addInsert", cart); 
	}
	@Override
	public int addInsert(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("mapper.cartMapper.addInsert", cart);
	}

	@Override
	public int updateQty(int c_no, int c_qty) throws Exception {
		Cart cart = new Cart(c_no,
				c_qty,
				null,
				null);
		return sqlSession.update("mapper.cartMapper.updateQty",cart);
	}
	@Override
	public int updateQty(Cart cart) throws Exception {
		return sqlSession.update("mapper.cartMapper.updateQty", cart);
	}
	

	@Override
	public int updateQtyBynoAndid(int c_qty, int p_no, String m_id) {
		
		Cart cart = new Cart(0,
				c_qty,
				new Product(p_no, null, 0, null, null, 0, null, null, 0, null, 0, null, null),
				m_id);
		return sqlSession.update("mapper.cartMapper.updateQtyBynoAndid", cart);
	}
	

	@Override
	public int removeCartAll(String m_id) throws Exception {

		return sqlSession.delete("mapper.cartMapper.removeCartAll", m_id);
	}

	@Override
	public int removeCart(int c_no) throws Exception {
		
		return sqlSession.delete("mapper.cartMapper.removeCart", c_no);
	}

	@Override
	public boolean productExist(Cart cart) throws Exception {
		boolean existProduct = false;
		int productCount = sqlSession.selectOne("mapper.cartMapper.productExist",cart);
		if (productCount==0) {
			existProduct=false;
		}else if(productCount > 0) {
			existProduct=true;
		}
		return existProduct;
	}
	@Override
	public boolean productExist(String m_id, int p_no) throws Exception {
		Cart cart = new Cart(0, 
							0, 
							new Product(p_no, null, 0, null, null, 0, null, null, 0, null, 0, null, null), 
							m_id);
		boolean existProduct = false;
		int productCount = sqlSession.selectOne("mapper.cartMapper.productExist",cart);
		if (productCount==0) {
			existProduct=false;
		}else if(productCount > 0) {
			existProduct=true;
		}
		return existProduct;
	}

	@Override
	public Cart getCartByNo(int c_no) throws Exception {
		return sqlSession.selectOne("mapper.cartMapper.getCartByNo",c_no);
	}

	@Override
	public List<Cart> cartListAll(String m_id) throws Exception {
		return sqlSession.selectList("mapper.cartMapper.cartListAll",m_id);
	}
	

	

	

	
}