package com.a2a2lab.module.order;

public class OrderDto{
	
	// orderMaster
	private String orderMasterId;
	private Integer status;
	private String statusName;
	private Integer price;
	private String createdAt;
	private String updatedAt;
	private Integer isDeleted;
	private String memberId;
	// member
	private String memberName;
	// orderDetail
	private Integer quantity;
	private Integer orderDetailPrice;
	// product
	private String productId;
	private String productName;
	private Integer rating;
	// file
	private String filePath;
	
	
	
	public String getOrderMasterId() {
		return orderMasterId;
	}
	public void setOrderMasterId(String orderMasterId) {
		this.orderMasterId = orderMasterId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
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
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getOrderDetailPrice() {
		return orderDetailPrice;
	}
	public void setOrderDetailPrice(Integer orderDetailPrice) {
		this.orderDetailPrice = orderDetailPrice;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}