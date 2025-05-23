package com.a2a2lab.module.codeGroup;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Repository
public interface CodeGroupDao {
	
	// 관리자 코드그룹 관리
	public int countCodeGroupsByVo(@Param("searchVo") SearchVo searchVo);
	public List<CodeGroupDto> findCodeGroupsByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo);
	
	// 코드그룹 전체검색
	public List<CodeGroupDto> findAllCodeGroup(); 
	
	// 코드그룹 상세
	public CodeGroupDto findCodeGroupById(String codegroupId);
	
	// Select를 제외한 CRUD
	public int createCodeGroup(CodeGroupDto dto);
	public int updateCodeGroup(CodeGroupDto dto);
	public int softDeleteCodeGroup(String codegroupId);
	public int hardDeleteCodeGroup(String codegroupId);
	
}
