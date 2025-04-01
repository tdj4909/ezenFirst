package com.a2a2lab.module.member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MemberDto {
	
	private String ifcgSeq;

	private String seq;
	private String email;
	private String password;
	private String nickname;
	private String lastNm;
	private String firstNm;
	private Integer gender;
	private String userDob;
	private Integer mobileCarrier;
	private String phone;
	private Integer admin;
	private Integer userDelNy;
	
	private String userFullNm;
	private String codeSeq;
	private String codeNm;
	
	public static List<MemberDto> cachedCodeArrayList = new ArrayList<MemberDto>();
	
	
	
	@Override
	public String toString() {
		return "MemberDto [ifcgSeq=" + ifcgSeq + ", seq=" + seq + ", email=" + email + ", password=" + password
				+ ", nickname=" + nickname + ", lastNm=" + lastNm + ", firstNm=" + firstNm + ", gender=" + gender
				+ ", userDob=" + userDob + ", mobileCarrier=" + mobileCarrier + ", phone=" + phone + ", admin=" + admin
				+ ", userDelNy=" + userDelNy + ", userFullNm=" + userFullNm + ", codeSeq=" + codeSeq + ", codeNm="
				+ codeNm + "]";
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getLastNm() {
		return lastNm;
	}
	public void setLastNm(String lastNm) {
		this.lastNm = lastNm;
	}
	public String getFirstNm() {
		return firstNm;
	}
	public void setFirstNm(String firstNm) {
		this.firstNm = firstNm;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	public String getUserDob() {
		return userDob;
	}
	public void setUserDob(String userDob) {
		this.userDob = userDob;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getAdmin() {
		return admin;
	}
	public void setAdmin(Integer admin) {
		this.admin = admin;
	}
	public Integer getUserDelNy() {
		return userDelNy;
	}
	public void setUserDelNy(Integer userDelNy) {
		this.userDelNy = userDelNy;
	}
	public String getCodeSeq() {
		return codeSeq;
	}
	public void setCodeSeq(String codeSeq) {
		this.codeSeq = codeSeq;
	}
	public String getCodeNm() {
		return codeNm;
	}
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}
	public Integer getMobileCarrier() {
		return mobileCarrier;
	}
	public void setMobileCarrier(Integer mobileCarrier) {
		this.mobileCarrier = mobileCarrier;
	}
	
	public String getUserFullNm() {
		return lastNm + firstNm;
	}
	
	public String getIfcgSeq() {
		return ifcgSeq;
	}
	public void setIfcgSeq(String ifcgSeq) {
		this.ifcgSeq = ifcgSeq;
	}
	
}
