package com.a2a2lab.module.member;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.a2a2lab.module.codeGroup.CodeGroupDto;
import com.a2a2lab.module.codeGroup.CodeGroupVo;

@Repository
public interface MemberDao {
	
	
	
	public int insert(MemberDto memberDto);
	public int selectOneCount();
	public List<MemberDto> selectList(MemberVo vo);

}
