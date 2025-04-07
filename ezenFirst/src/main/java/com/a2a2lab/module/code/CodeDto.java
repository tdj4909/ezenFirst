package com.a2a2lab.module.code;

import java.time.LocalDateTime;

public class CodeDto {

	private String ifcgSeq;
	
	
	private String seq;
	private String codeCd;
	private String codeNm;
	private Integer codeUse;
	private String codeRegDateTime;
	private String codeModDateTime;
	private Integer codeDelNy;
	private String codeGroup_seq;
	
//	---- 상속 ----
	private Integer codeGroupUse;
	private String codeGroupCd;
	private String codeGroupNm;
	
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCodeNm() {
		return codeNm;
	}
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}
	public String getCodeCd() {
		return codeCd;
	}
	public void setCodeCd(String codeCd) {
		this.codeCd = codeCd;
	}
	public Integer getCodeDelNy() {
		return codeDelNy;
	}
	public void setCodeDelNy(Integer codeDelNy) {
		this.codeDelNy = codeDelNy;
	}
	public String getCodeGroup_seq() {
		return codeGroup_seq;
	}
	public void setCodeGroup_seq(String codeGroup_seq) {
		this.codeGroup_seq = codeGroup_seq;
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
	public Integer getCodeUse() {
		return codeUse;
	}
	public void setCodeUse(Integer codeUse) {
		this.codeUse = codeUse;
	}
	
	public String getIfcgSeq() {
		return ifcgSeq;
	}
	public void setIfcgSeq(String ifcgSeq) {
		this.ifcgSeq = ifcgSeq;
	}
	
	public String getCodeRegDateTime() {
		return codeRegDateTime;
	}
	public void setCodeRegDateTime(String codeRegDateTime) {
		this.codeRegDateTime = codeRegDateTime;
	}
	public String getCodeModDateTime() {
		return codeModDateTime;
	}
	public void setCodeModDateTime(String codeModDateTime) {
		this.codeModDateTime = codeModDateTime;
	}
	
}
