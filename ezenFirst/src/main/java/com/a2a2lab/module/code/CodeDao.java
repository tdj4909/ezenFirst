package com.a2a2lab.module.code;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CodeDao {
	
	public int selectOneCount(CodeVo vo);
	public CodeDto selectOne(CodeVo vo);
	public List<CodeDto> selectList(CodeVo vo);
	public int insert(CodeDto codeDto);
	public int delete(CodeDto codeDto);
	public int uelete(CodeDto codeDto);
	public int update(CodeDto codeDto);
	public List<CodeDto> selectCodeGroup();
}
