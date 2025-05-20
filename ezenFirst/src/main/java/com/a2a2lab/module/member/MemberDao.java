package com.a2a2lab.module.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Repository
public interface MemberDao {
	
	public int countMembersByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo); // vo로 코드 개수 검색
	public List<MemberDto> findMembersByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo); // vo로 코드 검색
	public MemberDto findMemberById(String memberId);
	public MemberDto findMemberByEmail(String email);
	public int countAllMember();
	public int createMember(MemberDto dto);
	public int updateMember(MemberDto dto);
	public int softDeleteMember(String memberId);
	public int hardDeleteMember(String memberId);
	public int emailChk(String email);
	public int changePwd(MemberDto dto);
	
	
	
	
//	public int insert(MemberDto dto);
//	public int update(MemberDto dto);
//	public int uelete(MemberDto dto);
//	public int delete(MemberDto dto);
//	public int memberCount();
//	public int selectOneCount(MemberVo vo);
//	public MemberDto selectOne(MemberVo vo);
//	public MemberDto loginChk(MemberDto dto);
//	public int emailChk(MemberDto dto);
//	public List<MemberDto> selectList(MemberVo vo);
//	public List<MemberDto> selectMobileCarrierGroup();

	public List<MemberDto> selectListCachedCodeArrayList();
}
