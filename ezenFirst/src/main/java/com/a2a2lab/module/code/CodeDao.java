package com.a2a2lab.module.code;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Repository
public interface CodeDao {
	
	// 관리자 코드 관리
	public int countCodesByVo(@Param("searchVo") SearchVo searchVo);
	public List<CodeDto> findCodesByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo);
	
	// 코드 전체검색
	public List<CodeDto> findAllCodes();
	
	// 코드 상세
	public CodeDto findCodeById(String codeId);
	
	// codegroupId로 코드 검색
	public List<CodeDto> findCodesByCodeGroupId(String codegroupId); 
	
	// Select를 제외한 CRUD
	public int createCode(CodeDto dto);
	public int updateCode(CodeDto dto);
	public int softDeleteCode(String codeId);
	public int softDeleteCodeByCodeGroupId(String codegroupId);
	public int hardDeleteCode(String codeId);
}
