package com.kh.mybatis.member.model.vo;

import java.sql.Date;

public class Member {
	
	// 필드부
	private int userNo; 	 //	  USER_NO NUMBER PRIMARY KEY,               
	private String userId;   //	  USER_ID VARCHAR2(30) NOT NULL UNIQUE,   
	private String userPwd;  //	  USER_PWD VARCHAR2(100) NOT NULL,  
	private String userName; //	  USER_NAME VARCHAR2(15) NOT NULL,    
	private String email; 	 //	  EMAIL VARCHAR2(100),        
	private String birthday; //	  BIRTHDAY VARCHAR2(6),
	private String gender; 	 //	  GENDER VARCHAR2(1) CHECK (GENDER IN('M', 'F')),    
	private String phone;	 //	  PHONE VARCHAR2(13),            
	private String address;  //	  ADDRESS VARCHAR2(100),
	private Date enrollDate; //	  ENROLL_DATE DATE DEFAULT SYSDATE,
	private Date modifyDate; //	  MODIFY_DATE DATE DEFAULT SYSDATE,
	private String status;	 //	  STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N'))
	
	// 생성자부
	public Member() { }
	
	public Member(int userNo, String userId, String userPwd, String userName, String email, String birthday,
			String gender, String phone, String address, Date enrollDate, Date modifyDate, String status) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}
	
	// 회원가입용 생성자
	public Member(String userId, String userPwd, String userName, String email, String birthday, String gender,
			String phone, String address) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
	}

	// 메소드부 (Alt + Shift + S: Source 타고 들어가는 단축키)
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", email=" + email + ", birthday=" + birthday + ", gender=" + gender + ", phone=" + phone
				+ ", address=" + address + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", status="
				+ status + "]";
	}
	

}
