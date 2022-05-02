package com.itwill.tomorrowHome.domain;

public class Member {
/*
 이름         널?       유형            
---------- -------- ------------- 
M_ID       NOT NULL VARCHAR2(50)  
M_PASSWORD          VARCHAR2(50)  
M_NAME              VARCHAR2(50)  
M_EMAIL             VARCHAR2(50)  
M_ADDRESS           VARCHAR2(200) 
M_POST              VARCHAR2(20)  
M_PHONE             VARCHAR2(20) 	
 */
	private String m_id;
	private String m_password;
	private String m_name;
	private String m_email;
	private String m_address;
	private String m_post;
	private String m_phone;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String m_id, String m_password, String m_name, String m_email, String m_address, String m_post,
			String m_phone) {
		super();
		this.m_id = m_id;
		this.m_password = m_password;
		this.m_name = m_name;
		this.m_email = m_email;
		this.m_address = m_address;
		this.m_post = m_post;
		this.m_phone = m_phone;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_password() {
		return m_password;
	}

	public void setM_password(String m_password) {
		this.m_password = m_password;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getM_address() {
		return m_address;
	}

	public void setM_address(String m_address) {
		this.m_address = m_address;
	}

	public String getM_post() {
		return m_post;
	}

	public void setM_post(String m_post) {
		this.m_post = m_post;
	}

	public String getM_phone() {
		return m_phone;
	}

	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}

	@Override
	public String toString() {
		return "Member [m_id=" + m_id + ", m_password=" + m_password + ", m_name=" + m_name + ", m_email=" + m_email
				+ ", m_address=" + m_address + ", m_post=" + m_post + ", m_phone=" + m_phone + "]";
	}
	
}
