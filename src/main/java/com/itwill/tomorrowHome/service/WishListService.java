package com.itwill.tomorrowHome.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.itwill.tomorrowHome.domain.Product;
import com.itwill.tomorrowHome.domain.WishList;

@Transactional
public interface WishListService {
	

	//찜 목록 추가 및 변경
	int insertWish(String m_id,int p_no) throws Exception;
	int insertWish(WishList wishList)throws Exception;
	
	//찜 목록 1개 비우기
	int removeWishByNo(int c_no) throws Exception;
	

	//찜 목록 전체 비우기
	int removeWishAll(String m_id) throws Exception;
	
	//찜 목록 1개 상세정보 선택
		WishList getWishByNo(int c_no) throws Exception;
	
	//찜 목록 전체출력
		List<WishList> wishListAll(String m_id) throws Exception;
}
