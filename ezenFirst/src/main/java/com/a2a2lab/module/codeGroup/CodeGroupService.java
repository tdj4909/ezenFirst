package com.a2a2lab.module.codeGroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Service
public class CodeGroupService {

	@Autowired
	CodeGroupDao dao;
	
	public int countCodeGroupsByVo(SearchVo searchVo) {
		return dao.countCodeGroupsByVo(searchVo);
	}
	
	public List<CodeGroupDto> findCodeGroupsByVo(PageVo pageVo,  SearchVo searchVo) {
		return dao.findCodeGroupsByVo(pageVo, searchVo);
	}
	
	public CodeGroupDto findCodeGroupById(String codegroupId) {
		return dao.findCodeGroupById(codegroupId);
	}
	
	public int createCodeGroup(CodeGroupDto codeGroupDto) {
		return dao.createCodeGroup(codeGroupDto);
	}
	
	
	
	
	public int selectOneCount(CodeGroupVo vo) {
		return dao.selectOneCount(vo);
	}
	
	public CodeGroupDto selectOne(CodeGroupVo vo) {
		return dao.selectOne(vo);
	}
	
	public List<CodeGroupDto> selectList(CodeGroupVo vo) {
		return dao.selectList(vo);
	}
	
	public int insert(CodeGroupDto codeGroupDto) {
		return dao.insert(codeGroupDto);
	}
	
	public int update(CodeGroupDto codeGroupDto) {
		return dao.update(codeGroupDto);
	}

	public int delete(CodeGroupDto codeGroupDto) {
		return dao.delete(codeGroupDto);
	}

	public int uelete(CodeGroupDto codeGroupDto) {
		return dao.uelete(codeGroupDto);
	}

}
