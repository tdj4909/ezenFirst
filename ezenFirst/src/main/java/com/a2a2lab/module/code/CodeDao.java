package com.a2a2lab.module.code;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CodeDao {
	
	public int selectOneCount();
	public List<CodeDto> selectList(CodeVo vo);
	public int insert(CodeDto codeDto);
	public int delete(CodeDto codeDto);
	public int uelete(CodeDto codeDto);
}
