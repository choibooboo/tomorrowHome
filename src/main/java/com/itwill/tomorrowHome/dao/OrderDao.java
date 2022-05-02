package com.itwill.tomorrowHome.dao;

import java.util.List;

import com.itwill.tomorrowHome.domain.Order;


 
public interface OrderDao {
	/*
	 * 주문전체삭제(ON DELETE CASCADE)
	 */
	int delete(String sUserId) throws Exception;
	
	/*
	 * 주문1건삭제(ON DELETE CASCADE)
	 */
	int deleteByOrderNo(int o_no) throws Exception;

	/*
	 * 주문생성
	 */
	int create(Order order) throws Exception;

	/*
	 * 주문전체(특정사용자)
	 */
	List<Order> list(String sUserId) throws Exception;

	/*
	 * 주문1개보기(주문상세리스트)
	 */
	Order detail(int o_no) throws Exception;

	/*
	 * 멤버의 특정 상품 주문 여부 조회
	 */
	int selectMemberProductOrderCount(String sUserId, int p_no) throws Exception;

	

}
