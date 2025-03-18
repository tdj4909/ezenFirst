package com.a2a2lab.module.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.codeGroup.CodeGroupDto;
import com.a2a2lab.module.codeGroup.CodeGroupVo;

@Service
public class MemberService {

	@Autowired
	MemberDao memberDao;
	
	public int insert(MemberDto memberDto) {
		return memberDao.insert(memberDto);
	}

	public int selectOneCount() {
		return memberDao.selectOneCount();
	}

	public List<MemberDto> selectList(MemberVo vo) {
		return memberDao.selectList(vo);
	}

}
