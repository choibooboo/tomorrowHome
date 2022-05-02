package com.itwill.tomorrowHome.domain;

import java.util.Date;

public class Review {
	private int r_no;
	private Date r_date;
	private String r_content;
	private int r_score;
	private String r_title;
	private String m_id;
	private int p_no;
	
	public Review() {
	}
	
	public Review(int r_no, Date r_date, String r_content, int r_score, String r_title, String m_id, int p_no) {
		super();
		this.r_no = r_no;
		this.r_date = r_date;
		this.r_content = r_content;
		this.r_score = r_score;
		this.r_title = r_title;
		this.m_id = m_id;
		this.p_no = p_no;
	}

	public int getR_no() {
		return r_no;
	}

	public void setR_no(int r_no) {
		this.r_no = r_no;
	}

	public Date getR_date() {
		return r_date;
	}

	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}

	public String getR_content() {
		return r_content;
	}

	public void setR_content(String r_content) {
		this.r_content = r_content;
	}

	public int getR_score() {
		return r_score;
	}

	public void setR_score(int r_score) {
		this.r_score = r_score;
	}

	public String getR_title() {
		return r_title;
	}

	public void setR_title(String r_title) {
		this.r_title = r_title;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	@Override
	public String toString() {
		return "Review [r_no=" + r_no + ", r_date=" + r_date + ", r_content=" + r_content + ", r_score=" + r_score
				+ ", r_title=" + r_title + ", m_id=" + m_id + ", p_no=" + p_no + "]";
	}
	
	
}
