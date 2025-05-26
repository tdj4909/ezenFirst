package com.a2a2lab.module.codeGroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.code.CodeDao;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Service
public class CodeGroupService {

	@Autowired
	CodeGroupDao dao;
	
	@Autowired
	CodeDao codeDao;
	
	public int countCodeGroupsByVo(SearchVo searchVo) {
		return dao.countCodeGroupsByVo(searchVo);
	}
	
	public List<CodeGroupDto> findCodeGroupsByVo(PageVo pageVo, SearchVo searchVo) {
		return dao.findCodeGroupsByVo(pageVo, searchVo);
	}
	
	public List<CodeGroupDto> findAllCodeGroup() {
		return dao.findAllCodeGroup();
	}
	
	public CodeGroupDto findCodeGroupById(String id) {
		return dao.findCodeGroupById(id);
	}
	
	public int createCodeGroup(CodeGroupDto dto) {
		return dao.createCodeGroup(dto);
	}
	
	public int updateCodeGroup(CodeGroupDto dto) {
		return dao.updateCodeGroup(dto);
	}
	
	public int softDeleteCodeGroup(String id) {
		codeDao.softDeleteCodeByCodeGroupId(id);
		return dao.softDeleteCodeGroup(id);
	}
	
	public int hardDeleteCodeGroup(String id) {
		return dao.hardDeleteCodeGroup(id);
	}
	
}
