package com.itwill.tomorrowHome.dao;

import java.util.List;

import com.itwill.tomorrowHome.domain.WishList;

public interface WishListDao {
	
	/*
	 private int w_no;
	private String m_id;
	private Product product;
	 */
	//찜목록 추가
	int insertWish(String m_id,int p_no)throws Exception;
	int insertWish(WishList wishList)throws Exception;
	
	//찜 목록 1개 비우기
	int removeWishByNo(int w_no)throws Exception;
	
	//찜 목록 전체 비우기
	int removeWishAll(String m_id)throws Exception;
	
	//찜 목록에 이미 있는가
	boolean productWishExist(WishList wishList)throws Exception;
	boolean productWishExist(String m_id,int p_no)throws Exception;
	
	//찜 목록 1개 상세정보 선택
	WishList getWishByNo(int w_no)throws Exception;
	
	//찜 목록 전체출력
	List<WishList> wishListAll(String m_id)throws Exception;
	
			
}
