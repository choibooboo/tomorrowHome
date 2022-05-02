package com.itwill.tomorrowHome.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private int o_no;
	private String o_desc;
	private Date o_date;
	private int o_price;
	private String o_pay_method;
	private String o_status;
	private String o_rv_name;
	private String o_rv_phone;
	private String o_rv_address;
	private String o_rv_post;
	private String o_message;
	private String m_id;
	private List<OrderItem> orderItemList = new ArrayList<OrderItem>();

	public Order() {

		
	}

	public Order(int o_no, String o_desc, Date o_date, int o_price, String o_pay_method, String o_status,
			String o_rv_name, String o_rv_phone, String o_rv_address, String o_rv_post, String o_message, String m_id,
			List<OrderItem> orderItemList) {
		super();
		this.o_no = o_no;
		this.o_desc = o_desc;
		this.o_date = o_date;
		this.o_price = o_price;
		this.o_pay_method = o_pay_method;
		this.o_status = o_status;
		this.o_rv_name = o_rv_name;
		this.o_rv_phone = o_rv_phone;
		this.o_rv_address = o_rv_address;
		this.o_rv_post = o_rv_post;
		this.o_message = o_message;
		this.m_id = m_id;
		this.orderItemList = orderItemList;
	}

	public int getO_no() {
		return o_no;
	}

	public void setO_no(int o_no) {
		this.o_no = o_no;
	}

	public String getO_desc() {
		return o_desc;
	}

	public void setO_desc(String o_desc) {
		this.o_desc = o_desc;
	}

	public Date getO_date() {
		return o_date;
	}

	public void setO_date(Date o_date) {
		this.o_date = o_date;
	}

	public int getO_price() {
		return o_price;
	}

	public void setO_price(int o_price) {
		this.o_price = o_price;
	}

	public String getO_pay_method() {
		return o_pay_method;
	}

	public void setO_pay_method(String o_pay_method) {
		this.o_pay_method = o_pay_method;
	}

	public String getO_status() {
		return o_status;
	}

	public void setO_status(String o_status) {
		this.o_status = o_status;
	}

	public String getO_rv_name() {
		return o_rv_name;
	}

	public void setO_rv_name(String o_rv_name) {
		this.o_rv_name = o_rv_name;
	}

	public String getO_rv_phone() {
		return o_rv_phone;
	}

	public void setO_rv_phone(String o_rv_phone) {
		this.o_rv_phone = o_rv_phone;
	}

	public String getO_rv_address() {
		return o_rv_address;
	}

	public void setO_rv_address(String o_rv_address) {
		this.o_rv_address = o_rv_address;
	}

	public String getO_rv_post() {
		return o_rv_post;
	}

	public void setO_rv_post(String o_rv_post) {
		this.o_rv_post = o_rv_post;
	}

	public String getO_message() {
		return o_message;
	}

	public void setO_message(String o_message) {
		this.o_message = o_message;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	

	@Override
	public String toString() {
		return "Order [o_no=" + o_no + ", o_desc=" + o_desc + ", o_date=" + o_date + ", o_price=" + o_price
				+ ", o_pay_method=" + o_pay_method + ", o_status=" + o_status + ", o_rv_name=" + o_rv_name
				+ ", o_rv_phone=" + o_rv_phone + ", o_rv_address=" + o_rv_address + ", o_rv_post=" + o_rv_post
				+ ", o_message=" + o_message + ", m_id=" + m_id + ", orderItemList=" + orderItemList + "]";
	}
	
	
	
}
