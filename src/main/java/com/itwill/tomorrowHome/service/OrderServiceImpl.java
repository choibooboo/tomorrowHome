package com.itwill.tomorrowHome.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.tomorrowHome.dao.CartDao;
import com.itwill.tomorrowHome.dao.OrderDao;
import com.itwill.tomorrowHome.dao.ProductDao;
import com.itwill.tomorrowHome.domain.Cart;
import com.itwill.tomorrowHome.domain.Order;
import com.itwill.tomorrowHome.domain.OrderItem;
import com.itwill.tomorrowHome.domain.Product;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CartDao cartDao;

	/*
	 * 주문1개 삭제
	 */
	@Override
	public int deleteByOrderNo(int o_no) throws Exception {
		return orderDao.deleteByOrderNo(o_no);
	}

	/*
	 * 주문 전체 삭제
	 */
	@Override
	public int delete(String sUserId) throws Exception {
		return orderDao.delete(sUserId);
	}

	/*
	 * 주문목록
	 */
	@Override
	public List<Order> list(String sUserId) throws Exception {
		return orderDao.list(sUserId);
	}

	/*
	 * 주문 상세보기
	 */
	@Override
	public Order detail(int o_no) throws Exception {
		return orderDao.detail(o_no);
	}

	/*
	 * 상품에서 직접주문
	 */
	@Override
	public int create(int p_no, int oi_qty, Order order) throws Exception {
		Product product = productDao.selectProduct(p_no);
		OrderItem orderItem = new OrderItem(0, oi_qty, p_no, product);
		ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
		orderItemList.add(orderItem);
		// 주문이름 생성
		String o_desc = orderItemList.get(0).getProduct().getP_name();
		order.setO_desc(o_desc);
		order.setOrderItemList(orderItemList);
		return orderDao.create(order);
	}

	/*
	 * 카트 전체 주문
	 */
	@Override
	public int create(Order order) throws Exception {
		List<Cart> cartList = cartDao.cartListAll(order.getM_id());
		ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for (Cart cart : cartList) {
			OrderItem orderItem = new OrderItem(0, cart.getC_qty(), 0, cart.getProduct());
			orderItemList.add(orderItem);
		}
		// 주문이름 생성
		String order_desc = orderItemList.get(0).getProduct().getP_name();
		int size = orderItemList.size();
		if (size > 1) {
			order_desc += " 외 " + (size - 1) + "건";
		}
		order.setO_desc(order_desc);
		order.setOrderItemList(orderItemList);
		int result = orderDao.create(order);
		cartDao.removeCartAll(order.getM_id());
		return result;
	}

	/*
	 * cart에서 선택주문
	 */
	@Override
	public int create(String[] cart_item_no_array, Order order) throws Exception {
		ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for (int i = 0; i < cart_item_no_array.length; i++) {
			int c_no = Integer.parseInt(cart_item_no_array[i]);
			Cart cart = cartDao.getCartByNo(c_no);
			OrderItem orderItem = new OrderItem(0, cart.getC_qty(), 0, cart.getProduct());
			orderItemList.add(orderItem);
			cartDao.removeCart(c_no);
		}
		// 주문이름 생성
		String order_desc = orderItemList.get(0).getProduct().getP_name();
		int size = orderItemList.size();
		if (size > 1) {
			order_desc += " 외 " + (size - 1) + "건";
		}
		order.setO_desc(order_desc);
		order.setOrderItemList(orderItemList);
		int result = orderDao.create(order);
		return result;
	}

}
