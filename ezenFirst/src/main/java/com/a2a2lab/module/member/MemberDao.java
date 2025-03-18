package com.a2a2lab.module.member;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {
	
	public int selectOneCount();
	public List<MemberDto> selectList(MemberVo vo);
	public int insert(MemberDto codeDto);
	public int delete(MemberDto codeDto);
	public int uelete(MemberDto codeDto);
	public List<MemberDto> selectCodeGroup();
}
