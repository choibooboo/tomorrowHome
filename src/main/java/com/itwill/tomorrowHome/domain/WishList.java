package com.itwill.tomorrowHome.domain;

public class WishList {
	private int w_no;
	private String m_id;
	private Product product;
	public WishList() {
		// TODO Auto-generated constructor stub
	}
	public WishList(int w_no, String m_id, Product product) {
		super();
		this.w_no = w_no;
		this.m_id = m_id;
		this.product = product;
	}
	public int getW_no() {
		return w_no;
	}
	public void setW_no(int w_no) {
		this.w_no = w_no;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "WishList [w_no=" + w_no + ", m_id=" + m_id + ", product=" + product + "]";
	}
	
	
	
}
