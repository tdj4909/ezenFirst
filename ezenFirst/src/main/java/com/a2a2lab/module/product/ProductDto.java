package com.a2a2lab.module.product;

public class ProductDto {
	

	private String productId;
	private Integer type;
	private String name;
	private String description;
	private Integer price;
	private Integer calories;
	private Integer sugars;
	private Integer protein;
	private Integer fat;
	private Integer sodium;
	private Integer isRecommand;
	private Integer orderCount;
	private Integer stock;
	private Integer rating;
	private String createdAt;
	private String updatedAt;
	private Integer isDeleted;
	private String fileId;

	
//	private String path;
//	private String originalName;
//	
//	private String menuTypeNm;
//	
//	private String ifcgSeq;
	
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getCalories() {
		return calories;
	}
	public void setCalories(Integer calories) {
		this.calories = calories;
	}
	public Integer getSugars() {
		return sugars;
	}
	public void setSugars(Integer sugars) {
		this.sugars = sugars;
	}
	public Integer getProtein() {
		return protein;
	}
	public void setProtein(Integer protein) {
		this.protein = protein;
	}
	public Integer getFat() {
		return fat;
	}
	public void setFat(Integer fat) {
		this.fat = fat;
	}
	public Integer getSodium() {
		return sodium;
	}
	public void setSodium(Integer sodium) {
		this.sodium = sodium;
	}
	public Integer getIsRecommand() {
		return isRecommand;
	}
	public void setIsRecommand(Integer isRecommand) {
		this.isRecommand = isRecommand;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
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
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
}
