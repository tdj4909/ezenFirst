package com.a2a2lab.module.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

	@Autowired
	CodeDao codeDao;
	
	public int selectOneCount(CodeVo vo) {
		return codeDao.selectOneCount(vo);
	}
	
	public CodeDto selectOne(CodeVo vo) {
		return codeDao.selectOne(vo);
	}
	
	public List<CodeDto> selectList(CodeVo vo) {
		return codeDao.selectList(vo);
	}
	
	public int insert(CodeDto codeDto) {
		return codeDao.insert(codeDto);
	}

	public int delete(CodeDto codeDto) {
		return codeDao.delete(codeDto);
	}

	public int uelete(CodeDto codeDto) {
		return codeDao.uelete(codeDto);
	}
	
	public int update(CodeDto codeDto) {
		return codeDao.update(codeDto);
	}
	
	public List<CodeDto> selectCodeGroup() {
		return codeDao.selectCodeGroup();
	}

}
