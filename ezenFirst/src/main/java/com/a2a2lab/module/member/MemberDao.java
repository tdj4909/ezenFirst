package com.a2a2lab.module.member;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {
	
	public int insert(MemberDto dto);
	public int update(MemberDto dto);
	public int uelete(MemberDto dto);
	public int delete(MemberDto dto);
	public int memberCount();
	public int selectOneCount(MemberVo vo);
	public MemberDto selectOne(MemberVo vo);
	public MemberDto loginChk(MemberDto dto);
	public int emailChk(MemberDto dto);
	public List<MemberDto> selectList(MemberVo vo);
	public List<MemberDto> selectMobileCarrierGroup();

	public List<MemberDto> selectListCachedCodeArrayList();
}
