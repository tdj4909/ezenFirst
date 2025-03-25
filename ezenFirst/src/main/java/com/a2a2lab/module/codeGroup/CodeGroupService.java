package com.a2a2lab.module.codeGroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeGroupService {

	@Autowired
	CodeGroupDao codeGroupDao;
	
	public int selectOneCount(CodeGroupVo vo) {
		return codeGroupDao.selectOneCount(vo);
	}
	
	public CodeGroupDto selectOne(CodeGroupVo vo) {
		return codeGroupDao.selectOne(vo);
	}
	
	public List<CodeGroupDto> selectList(CodeGroupVo vo) {
		return codeGroupDao.selectList(vo);
	}
	
	public int insert(CodeGroupDto codeGroupDto) {
		return codeGroupDao.insert(codeGroupDto);
	}
	
	public int update(CodeGroupDto codeGroupDto) {
		return codeGroupDao.update(codeGroupDto);
	}

	public int delete(CodeGroupDto codeGroupDto) {
		return codeGroupDao.delete(codeGroupDto);
	}

	public int uelete(CodeGroupDto codeGroupDto) {
		return codeGroupDao.uelete(codeGroupDto);
	}

}
