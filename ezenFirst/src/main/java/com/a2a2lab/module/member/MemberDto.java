package com.a2a2lab.module.member;

public class MemberDto {

	private String seq;
	private String codeCd;
	private String codeNm;
	private Integer codeUse;
	private Integer codeDelNy;
	private Integer codeGroup_seq;
	
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
	public Integer getCodeGroup_seq() {
		return codeGroup_seq;
	}
	public void setCodeGroup_seq(Integer codeGroup_seq) {
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
	
}
