package com.a2a2lab.module.codeGroup;

public class CodeGroupDto {

	private String codegroupId;
	private String name;
	private String createdAt;
	private String updatedAt;
	private Integer isUsed;
	
	private Integer codeCount;
	
	
	// Getter, Setter
	public String getCodegroupId() {
		return codegroupId;
	}
	public void setCodegroupId(String codegroupId) {
		this.codegroupId = codegroupId;
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
	public Integer getCodeCount() {
		return codeCount;
	}
	public void setCodeCount(Integer codeCount) {
		this.codeCount = codeCount;
	}
}
