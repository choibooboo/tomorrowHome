package com.itwill.tomorrowHome.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.tomorrowHome.domain.Order;
import com.itwill.tomorrowHome.domain.OrderItem;
@Repository
public class OrderDaoImpl implements OrderDao{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int delete(String sUserId) throws Exception {
		return sqlSession.delete("mapper.OrderMapper.delete", sUserId);
	}

	@Override
	public int deleteByOrderNo(int o_no) throws Exception {
		return sqlSession.delete("mapper.OrderMapper.deleteByOrderNo", o_no);
	}

	@Override
	public int create(Order order) throws Exception {
		int resultCount = sqlSession.insert("mapper.OrderMapper.createOrder",order);
		List<OrderItem> orderItemList = order.getOrderItemList();
		for(OrderItem orderItem : orderItemList) {
			sqlSession.insert("mapper.OrderMapper.createOrderItem",orderItem);
		}
		return resultCount;
	}

	@Override
	public List<Order> list(String sUserId) throws Exception {
		return sqlSession.selectList("mapper.OrderMapper.list",sUserId);
	}

	@Override
	public Order detail(int o_no) throws Exception {
		return sqlSession.selectOne("mapper.OrderMapper.detail", o_no);
	}
	
	@Override
	public int selectMemberProductOrderCount(String sUserId, int p_no) throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("m_id", sUserId);
		map.put("p_no", p_no);
		return sqlSession.selectOne("mapper.OrderMapper.selectMemberProductOrderCount", map);
	}
	
}
