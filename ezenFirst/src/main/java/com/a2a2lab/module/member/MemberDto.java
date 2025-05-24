package com.a2a2lab.module.member;

public class MemberDto {
	
	private String memberId;
	private String email;
	private String password;
	private String name;
	private Integer gender;
	private String genderName;
	private String birthday;
	private Integer mobileCarrier;
	private String mobileCarrierName;
	private String phone;
	private String createdAt;
	private String updatedAt;
	
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getMobileCarrier() {
		return mobileCarrier;
	}

	public void setMobileCarrier(Integer mobileCarrier) {
		this.mobileCarrier = mobileCarrier;
	}

	public String getMobileCarrierName() {
		return mobileCarrierName;
	}

	public void setMobileCarrierName(String mobileCarrierName) {
		this.mobileCarrierName = mobileCarrierName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}
