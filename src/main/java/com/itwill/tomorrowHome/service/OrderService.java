package com.itwill.tomorrowHome.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.itwill.tomorrowHome.domain.Order;

@Transactional
public interface OrderService {
	/*
	 * 주문1개 삭제
	 */
	int deleteByOrderNo(int o_no) throws Exception;
	
	/*
	 * 주문 전체 삭제
	 */
	int delete(String sUserId) throws Exception;
	
	/*
	 * 주문목록
	 */
	List<Order> list(String sUserId) throws Exception;
	
	/*
	 * 주문 상세보기
	 */
	Order detail(int o_no) throws Exception;
	
	/*
	 * 상품에서 직접주문
	 */
	int create(int p_no, int oi_qty, Order order) throws Exception;
	
	/*
	 * cart에서 주문
	 */
	int create(Order order) throws Exception;
	
	/*
	 * cart에서 선택주문
	 */
	int create(String[] cart_item_no_array, Order order) throws Exception;
	

	
}
