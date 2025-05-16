package com.a2a2lab.module.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Service
public class CodeService {

	@Autowired
	CodeDao dao;
	
	public int countCodesByVo(PageVo pageVo, SearchVo searchVo) {
		return dao.countCodesByVo(pageVo, searchVo);
	}
	
	public List<CodeDto> findCodesByVo(PageVo pageVo, SearchVo searchVo) {
		return dao.findCodesByVo(pageVo, searchVo);
	}
	
	public CodeDto findCodeById(String id) {
		return dao.findCodeById(id);
	}
	
	public List<CodeDto> findCodesByCodeGroupId(String codegroupId) {
		return dao.findCodesByCodeGroupId(codegroupId);
	}
	
	public int createCode(CodeDto dto) {
		return dao.createCode(dto);
	}
	
	public int updateCode(CodeDto dto) {
		return dao.updateCode(dto);
	}
	
	public int softDeleteCode(String id) {
		return dao.softDeleteCode(id);
	}
	
	public int hardDeleteCode(String id) {
		return dao.hardDeleteCode(id);
	}

}
