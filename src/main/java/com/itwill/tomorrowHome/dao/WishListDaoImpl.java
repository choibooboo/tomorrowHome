package com.itwill.tomorrowHome.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.tomorrowHome.domain.Product;
import com.itwill.tomorrowHome.domain.WishList;

@Repository
public class WishListDaoImpl implements WishListDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertWish(String m_id, int p_no) throws Exception {
		WishList wishList = new WishList(0, m_id, new Product(p_no, null, 0, null, null, 0, null, null, 0, null, 0, null, null));
		
		return sqlSession.insert("mapper.WishListMapper.insertWish",wishList);
	}
	
	@Override
	public int insertWish(WishList wishList) throws Exception {
		return sqlSession.insert("mapper.WishListMapper.insertWish", wishList);
	}
	


	@Override
	public int removeWishByNo(int w_no) throws Exception {
		return sqlSession.delete("mapper.WishListMapper.removeWishByNo", w_no);
	}

	@Override
	public int removeWishAll(String m_id) throws Exception {
	
		return sqlSession.delete("mapper.WishListMapper.removeWishAll", m_id);
	}

	@Override
	public boolean productWishExist(WishList wishList) throws Exception {
		boolean productExist = false;
		int productCount = sqlSession.selectOne("mapper.WishListMapper.productWishExist", wishList);
		if (productCount==0) {
			productExist=false;
		}else if (productCount>0) {
			productExist=true;
		}
		return productExist;
	}
	@Override
	public boolean productWishExist(String m_id,int p_no) throws Exception {
		WishList wishList = new WishList(0, m_id, new Product(p_no, null, 0, null, null, 0, null, null, 0, null, 0, null, null));
		boolean productExist = false;
		int productCount = sqlSession.selectOne("mapper.WishListMapper.productWishExist", wishList);
		if (productCount==0) {
			productExist=false;
		}else if (productCount>0) {
			productExist=true;
		}
		return productExist;
	}

	@Override
	public WishList getWishByNo(int w_no) throws Exception {
		return sqlSession.selectOne("mapper.WishListMapper.getWishByNo",w_no);
	}

	@Override
	public List<WishList> wishListAll(String m_id) throws Exception {
		
		return sqlSession.selectList("mapper.WishListMapper.wishListAll", m_id);
	}



	


	

}
