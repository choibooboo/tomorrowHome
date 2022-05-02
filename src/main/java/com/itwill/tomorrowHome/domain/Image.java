package com.itwill.tomorrowHome.domain;

public class Image {
	private int im_no;
	private String im_div;
	private String im_name;
	private int p_no;
	
	public Image() {
	}

	public Image(int im_no, String im_div, String im_name, int p_no) {
		super();
		this.im_no = im_no;
		this.im_div = im_div;
		this.im_name = im_name;
		this.p_no = p_no;
	}

	public int getIm_no() {
		return im_no;
	}

	public void setIm_no(int im_no) {
		this.im_no = im_no;
	}

	public String getIm_div() {
		return im_div;
	}

	public void setIm_div(String im_div) {
		this.im_div = im_div;
	}

	public String getIm_name() {
		return im_name;
	}

	public void setIm_name(String im_name) {
		this.im_name = im_name;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	@Override
	public String toString() {
		return "Image [im_no=" + im_no + ", im_div=" + im_div + ", im_name=" + im_name + ", p_no=" + p_no + "]";
	}
	
}
