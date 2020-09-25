package com.bean;

public class StudentBean {

	/*
	 * create table student(sid serial primary key,sname varchar(30),semail
	 * varchar(30) unique,spassword varchar(30),sage int)
	 */
	private int sId;
	private String sName;
	private String sEmail;
	private String sPassword;
	private int sAge;

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsPassword() {
		return sPassword;
	}

	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}

	public int getsAge() {
		return sAge;
	}

	public void setsAge(int sAge) {
		this.sAge = sAge;
	}

}
