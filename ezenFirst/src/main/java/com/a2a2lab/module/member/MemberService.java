package com.a2a2lab.module.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	MemberDao codeDao;
	
	public int selectOneCount() {
		return codeDao.selectOneCount();
	}
	public List<MemberDto> selectList(MemberVo vo) {
		return codeDao.selectList(vo);
	}
	
	public int insert(MemberDto codeDto) {
		return codeDao.insert(codeDto);
	}

	public int delete(MemberDto codeDto) {
		return codeDao.delete(codeDto);
	}

	public int uelete(MemberDto codeDto) {
		return codeDao.uelete(codeDto);
	}
	
	public List<MemberDto> selectCodeGroup() {
		return codeDao.selectCodeGroup();
	}

}
