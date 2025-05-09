package com.a2a2lab.module.codeGroup;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Repository
public interface CodeGroupDao {
	
	public int countCodeGroupsByVo(SearchVo searchVo); // vo로 코드그룹 개수 검색
	public List<CodeGroupDto> findCodeGroupsByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo); // vo로 코드그룹 검색
	public CodeGroupDto findCodeGroupById(String codegroupId);
	public int createCodeGroup(CodeGroupDto codeGroupDto);
	
	
	public int selectOneCount(CodeGroupVo vo);
	public CodeGroupDto selectOne(CodeGroupVo vo);
	public List<CodeGroupDto> selectList(CodeGroupVo vo);
	public int insert(CodeGroupDto codeGroupDto);
	public int update(CodeGroupDto codeGroupDto);
	public int delete(CodeGroupDto codeGroupDto);
	public int uelete(CodeGroupDto codeGroupDto);
}
