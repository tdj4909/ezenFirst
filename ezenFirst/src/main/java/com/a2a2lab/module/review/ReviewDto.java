package com.a2a2lab.module.review;

public class ReviewDto{
	
	private String seq;
	private String reviewDate;
	private Integer reviewRate;
	private String reviewDescription;
	private Integer reviewDelNy;
	private Integer user_seq;
	private Integer menu_seq;
	
	private String nickname;
	private String menuNm;
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public Integer getReviewRate() {
		return reviewRate;
	}
	public void setReviewRate(Integer reviewRate) {
		this.reviewRate = reviewRate;
	}
	public String getReviewDescription() {
		return reviewDescription;
	}
	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}
	public Integer getReviewDelNy() {
		return reviewDelNy;
	}
	public void setReviewDelNy(Integer reviewDelNy) {
		this.reviewDelNy = reviewDelNy;
	}
	public Integer getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(Integer user_seq) {
		this.user_seq = user_seq;
	}
	public Integer getMenu_seq() {
		return menu_seq;
	}
	public void setMenu_seq(Integer menu_seq) {
		this.menu_seq = menu_seq;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

}