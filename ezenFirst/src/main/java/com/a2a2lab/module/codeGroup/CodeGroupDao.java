package com.a2a2lab.module.codeGroup;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CodeGroupDao {
	
	public List<CodeGroupDto> selectList();
	public int insert(CodeGroupDto codeGroupDto);
	public int delete(CodeGroupDto codeGroupDto);
	public int uelete(CodeGroupDto codeGroupDto);
}
