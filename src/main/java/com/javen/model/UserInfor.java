package com.javen.model;

public class UserInfor {
	
	private String first_name;
	private String last_name;
	private String personal_gender;
	private String street;
	private String email;
	private Integer phone_num;
	private Integer infor_Id;
	
	public String getFirstName() {
		return this.first_name;
	}
	
	public String getLastName() {
		return this.last_name;
	}
	
	public String getGender() {
		return this.personal_gender;
	}
	
	public String getStreet() {
		return this.street;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public Integer getPnoneNum() {
		return this.phone_num;
	}
	
	public Integer getId() {
		return this.infor_Id;
	}

	
	public void setFirstName(String given) {
		this.first_name = given;
	}
	
	public void setLastName(String given) {
		this.last_name = given;
	}
	
	public void setGender(String given) {
		this.personal_gender = given; 
	}
	
	public void setStreet(String given) {
		this.street = given;
	}
	
	public void setEmail(String given) {
		this.email = given;
	}
	
	public void setPnoneNum(Integer given) {
		this.phone_num = given;
	}
	
	public void setId(Integer given) {
		this.infor_Id = given;
	}
}
