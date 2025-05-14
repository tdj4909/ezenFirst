package com.a2a2lab.module.code;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Repository
public interface CodeDao {
	
	public int countCodesByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo); // vo로 코드 개수 검색
	public List<CodeDto> findCodesByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo); // vo로 코드 검색
	public CodeDto findCodeById(String codeId);
	public List<CodeDto> findCodesByCodeGroupId(String codegroupId); // codegroupId로 코드 검색
	public int createCode(CodeDto dto);
	public int updateCode(CodeDto dto);
	public int softDeleteCode(String codeId);
	public int hardDeleteCode(String codeId);
}
