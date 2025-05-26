package com.a2a2lab.module.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Repository
public interface MemberDao {
	
	// 관리자 회원 관리
	public int countMembersByVo(@Param("searchVo") SearchVo searchVo);
	public List<MemberDto> findMembersByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo);
	public MemberDto findMemberById(String memberId);
	public MemberDto findMemberByEmail(String email);
	
	// 회원 통계
	public int countAllMember();

	// 회원 가입
	public int emailChk(String email);
	public int changePwd(MemberDto dto);
	
	// Select를 제외한 CRUD
	public int createMember(MemberDto dto);
	public int updateMember(MemberDto dto);
	public int softDeleteMember(String memberId);
	public int hardDeleteMember(String memberId);
	
}
