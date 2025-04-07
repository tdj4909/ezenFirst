package com.a2a2lab.module.orders;

import java.time.LocalDateTime;

public class OrdersDto{
	
	// order
	private String ordersDate;
	private String seq;
	private Integer ordersStatus;
	private Integer totalPrice;
	private Integer takeOut;
	private Integer ordersDelNy;
	private String user_seq;
	
	// user
	private String nickname;
	
	// orderMenu
	private String orderMenu_seq;
	private Integer quantity;
	private Integer price;
	private String orders_seq;
	private String menu_seq;
	
	private Integer oneTotal;
		
	
	// menu
	private String menuImg;
	private String menuNm;
	private Integer menuPrice;
	
	
	public String getOrdersDate() {
		return ordersDate;
	}
	public void setOrdersDate(String ordersDate) {
		this.ordersDate = ordersDate;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public Integer getOrdersStatus() {
		return ordersStatus;
	}
	public void setOrdersStatus(Integer ordersStatus) {
		this.ordersStatus = ordersStatus;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getTakeOut() {
		return takeOut;
	}
	public void setTakeOut(Integer takeOut) {
		this.takeOut = takeOut;
	}
	public Integer getOrdersDelNy() {
		return ordersDelNy;
	}
	public void setOrdersDelNy(Integer ordersDelNy) {
		this.ordersDelNy = ordersDelNy;
	}
	public String getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getOrderMenu_seq() {
		return orderMenu_seq;
	}
	public void setOrderMenu_seq(String orderMenu_seq) {
		this.orderMenu_seq = orderMenu_seq;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getOrders_seq() {
		return orders_seq;
	}
	public void setOrders_seq(String orders_seq) {
		this.orders_seq = orders_seq;
	}
	public String getMenu_seq() {
		return menu_seq;
	}
	public void setMenu_seq(String menu_seq) {
		this.menu_seq = menu_seq;
	}
	public String getMenuImg() {
		return menuImg;
	}
	public void setMenuImg(String menuImg) {
		this.menuImg = menuImg;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public Integer getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(Integer menuPrice) {
		this.menuPrice = menuPrice;
	}
	
	public Integer getOneTotal() {
		return oneTotal;
	}
	public void setOneTotal(Integer oneTotal) {
		this.oneTotal = oneTotal;
	}
	

}