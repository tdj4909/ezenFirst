package com.a2a2lab.module.code;

public class CodeDto {

	private String codeId;
	private String name;
	private String createdAt;
	private String updatedAt;
	private Integer isUsed;
	private String codegroupId;
	private String codegroupName;
	
	
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}
	public String getCodegroupId() {
		return codegroupId;
	}
	public void setCodegroupId(String codegroupId) {
		this.codegroupId = codegroupId;
	}
	public String getCodegroupName() {
		return codegroupName;
	}
	public void setCodegroupName(String codegroupName) {
		this.codegroupName = codegroupName;
	}
}
