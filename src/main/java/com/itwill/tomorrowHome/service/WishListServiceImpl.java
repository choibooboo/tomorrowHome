package com.itwill.tomorrowHome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.tomorrowHome.dao.WishListDao;
import com.itwill.tomorrowHome.domain.WishList;

@Service
public class WishListServiceImpl implements WishListService{
	@Autowired
	private WishListDao wishListDao;
	
	@Override
	public int insertWish(String m_id,int p_no) throws Exception {
		
		if (wishListDao.productWishExist(m_id, p_no)){
			System.out.println("이미 찜 목록에 존재합니다.");
			return 0;
		}else {
			return wishListDao.insertWish(m_id, p_no);
		}
		
	}
	@Override
	public int insertWish(WishList wishList) throws Exception {
		
		if (wishListDao.productWishExist(wishList.getM_id(),wishList.getProduct().getP_no())) {
			System.out.println("이미 찜 목록에 존재합니다.");
			return 0;
		}else {
			return wishListDao.insertWish(wishList.getM_id(),wishList.getProduct().getP_no());
		}
		
		
	}

	@Override
	public int removeWishByNo(int c_no) throws Exception {
		// TODO Auto-generated method stub
		return wishListDao.removeWishByNo(c_no);
	}

	@Override
	public int removeWishAll(String m_id) throws Exception {
		return wishListDao.removeWishAll(m_id);
	}

	@Override
	public WishList getWishByNo(int c_no) throws Exception {
		return wishListDao.getWishByNo(c_no);
	}

	@Override
	public List<WishList> wishListAll(String m_id) throws Exception {
		return wishListDao.wishListAll(m_id);
	}



}
