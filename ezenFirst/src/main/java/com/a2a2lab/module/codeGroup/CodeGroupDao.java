package com.a2a2lab.module.codeGroup;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Repository
public interface CodeGroupDao {
	
	public int countCodeGroupsByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo); // vo로 코드그룹 개수 검색
	public List<CodeGroupDto> findCodeGroupsByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo); // vo로 코드그룹 검색
	public List<CodeGroupDto> findAllCodeGroup(); // 코드그룹 전체검색
	public CodeGroupDto findCodeGroupById(String codegroupId);
	public int createCodeGroup(CodeGroupDto dto);
	public int updateCodeGroup(CodeGroupDto dto);
	public int softDeleteCodeGroup(String codegroupId);
	public int hardDeleteCodeGroup(String codegroupId);
	
}
