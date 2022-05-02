package com.itwill.tomorrowHome.domain;

import java.util.Date;
import java.util.List;

public class Product {
	private int p_no;
	private String p_name;
	private int p_price;
	private String p_desc;
	private Date p_date;
	private int p_avg_score;
	private String p_color;
	private String p_brand;
	private int p_count;
	private String p_concept;
	private int cg_no;
	private List<Image> imageList;
	private List<Review> reviewList;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int p_no, String p_name, int p_price, String p_desc, Date p_date, int p_avg_score, String p_color,
			String p_brand, int p_count, String p_concept, int cg_no, List<Image> imageList, List<Review> reviewList) {
		super();
		this.p_no = p_no;
		this.p_name = p_name;
		this.p_price = p_price;
		this.p_desc = p_desc;
		this.p_date = p_date;
		this.p_avg_score = p_avg_score;
		this.p_color = p_color;
		this.p_brand = p_brand;
		this.p_count = p_count;
		this.p_concept = p_concept;
		this.cg_no = cg_no;
		this.imageList = imageList;
		this.reviewList = reviewList;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public String getP_desc() {
		return p_desc;
	}

	public void setP_desc(String p_desc) {
		this.p_desc = p_desc;
	}

	public Date getP_date() {
		return p_date;
	}

	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}

	public int getP_avg_score() {
		return p_avg_score;
	}

	public void setP_avg_score(int p_avg_score) {
		this.p_avg_score = p_avg_score;
	}

	public String getP_color() {
		return p_color;
	}

	public void setP_color(String p_color) {
		this.p_color = p_color;
	}

	public String getP_brand() {
		return p_brand;
	}

	public void setP_brand(String p_brand) {
		this.p_brand = p_brand;
	}

	public int getP_count() {
		return p_count;
	}

	public void setP_count(int p_count) {
		this.p_count = p_count;
	}

	public String getP_concept() {
		return p_concept;
	}

	public void setP_concept(String p_concept) {
		this.p_concept = p_concept;
	}

	public int getCg_no() {
		return cg_no;
	}

	public void setCg_no(int cg_no) {
		this.cg_no = cg_no;
	}

	public List<Image> getImageList() {
		return imageList;
	}

	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}

	public List<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "Product [p_no=" + p_no + ", p_name=" + p_name + ", p_price=" + p_price + ", p_desc=" + p_desc
				+ ", p_date=" + p_date + ", p_avg_score=" + p_avg_score + ", p_color=" + p_color + ", p_brand="
				+ p_brand + ", p_count=" + p_count + ", p_concept=" + p_concept + ", cg_no=" + cg_no + ", imageList="
				+ (imageList != null ? imageList.subList(0, Math.min(imageList.size(), maxLen)) : null)
				+ ", reviewList="
				+ (reviewList != null ? reviewList.subList(0, Math.min(reviewList.size(), maxLen)) : null) + "]";
	}
	
	
}
