package com.itwill.tomorrowHome.domain;

import java.sql.Date;

public class Qna {
	private int q_no;
	private String q_title;
	private String q_content;
	private Date q_date;
	private int q_count;
	//글의 논리적인 순서번호를 관리하기 위한 필드 (3개)
	private int q_group_no;    // 그룹번호 
	private int q_step;        // 그룹내 순서정보 
	private int q_depth;       // 답글 깊이 
	private String m_id;
	
	public Qna() {
	}

	public Qna(int q_no, String q_title, String q_content, Date q_date, int q_count, int q_group_no, int q_step,
			int q_depth, String m_id) {
		super();
		this.q_no = q_no;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_date = q_date;
		this.q_count = q_count;
		this.q_group_no = q_group_no;
		this.q_step = q_step;
		this.q_depth = q_depth;
		this.m_id = m_id;
	}

	public int getQ_no() {
		return q_no;
	}

	public void setQ_no(int q_no) {
		this.q_no = q_no;
	}

	public String getQ_title() {
		return q_title;
	}

	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}

	public String getQ_content() {
		return q_content;
	}

	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}

	public Date getQ_date() {
		return q_date;
	}

	public void setQ_date(Date q_date) {
		this.q_date = q_date;
	}

	public int getQ_count() {
		return q_count;
	}

	public void setQ_count(int q_count) {
		this.q_count = q_count;
	}

	public int getQ_group_no() {
		return q_group_no;
	}

	public void setQ_group_no(int q_group_no) {
		this.q_group_no = q_group_no;
	}

	public int getQ_step() {
		return q_step;
	}

	public void setQ_step(int q_step) {
		this.q_step = q_step;
	}

	public int getQ_depth() {
		return q_depth;
	}

	public void setQ_depth(int q_depth) {
		this.q_depth = q_depth;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	@Override
	public String toString() {
		return "Qna [q_no=" + q_no + ", " + (q_title != null ? "q_title=" + q_title + ", " : "")
				+ (q_content != null ? "q_content=" + q_content + ", " : "")
				+ (q_date != null ? "q_date=" + q_date + ", " : "") + "q_count=" + q_count + ", q_group_no="
				+ q_group_no + ", q_step=" + q_step + ", q_depth=" + q_depth + ", "
				+ (m_id != null ? "m_id=" + m_id : "") + "]";
	}
	
}
