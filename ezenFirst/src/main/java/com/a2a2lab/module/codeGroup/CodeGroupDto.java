package com.a2a2lab.module.codeGroup;

import java.time.LocalDateTime;

public class CodeGroupDto {

	private String seq;
	private Integer codeGroupUse;
	private String codeGroupCd;
	private String codeGroupNm;
	private LocalDateTime codeGroupRegDateTime;
	private LocalDateTime codeGroupModDateTime;
	private Integer codeGroupDelNy;
	
	private Integer codeCnt;
	
	private String ifcgSeq;
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public Integer getCodeGroupUse() {
		return codeGroupUse;
	}
	public void setCodeGroupUse(Integer codeGroupUse) {
		this.codeGroupUse = codeGroupUse;
	}
	public String getCodeGroupCd() {
		return codeGroupCd;
	}
	public void setCodeGroupCd(String codeGroupCd) {
		this.codeGroupCd = codeGroupCd;
	}
	public String getCodeGroupNm() {
		return codeGroupNm;
	}
	public void setCodeGroupNm(String codeGroupNm) {
		this.codeGroupNm = codeGroupNm;
	}
	public Integer getCodeGroupDelNy() {
		return codeGroupDelNy;
	}
	public void setCodeGroupDelNy(Integer codeGroupDelNy) {
		this.codeGroupDelNy = codeGroupDelNy;
	}
	public Integer getCodeCnt() {
		return codeCnt;
	}
	public void setCodeCnt(Integer codeCnt) {
		this.codeCnt = codeCnt;
	}
	public String getIfcgSeq() {
		return ifcgSeq;
	}
	public void setIfcgSeq(String ifcgSeq) {
		this.ifcgSeq = ifcgSeq;
	}
	public LocalDateTime getCodeGroupRegDateTime() {
		return codeGroupRegDateTime;
	}
	public void setCodeGroupRegDateTime(LocalDateTime codeGroupRegDateTime) {
		this.codeGroupRegDateTime = codeGroupRegDateTime;
	}
	public LocalDateTime getCodeGroupModDateTime() {
		return codeGroupModDateTime;
	}
	public void setCodeGroupModDateTime(LocalDateTime codeGroupModDateTime) {
		this.codeGroupModDateTime = codeGroupModDateTime;
	}
}
