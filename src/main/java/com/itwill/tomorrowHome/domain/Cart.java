package com.itwill.tomorrowHome.domain;

public class Cart {
	private int c_no;
	private int c_qty;
	private Product product;
	private String m_id;
	public Cart() {
		// TODO Auto-generated constructor stub
	}
	public Cart(int c_no, int c_qty, Product product, String m_id) {
		super();
		this.c_no = c_no;
		this.c_qty = c_qty;
		this.product = product;
		this.m_id = m_id;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public int getC_qty() {
		return c_qty;
	}
	public void setC_qty(int c_qty) {
		this.c_qty = c_qty;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	@Override
	public String toString() {
		return "Cart [c_no=" + c_no + ", c_qty=" + c_qty + ", product=" + product + ", m_id=" + m_id + "]";
	}
	
	
}
