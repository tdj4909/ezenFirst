package com.a2a2lab.module.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.codeGroup.CodeGroupDto;
import com.a2a2lab.module.codeGroup.CodeGroupVo;

import jakarta.annotation.PostConstruct;

@Service
public class MemberService {

	@Autowired
	MemberDao memberDao;
	
	public int insert(MemberDto memberDto) {
		return memberDao.insert(memberDto);
	}

	public int selectOneCount(MemberVo vo) {
		return memberDao.selectOneCount(vo);
	}
	
	public int loginChk(MemberDto dto) {
		return memberDao.loginChk(dto);
	}

	public List<MemberDto> selectList(MemberVo vo) {
		return memberDao.selectList(vo);
	}
	
	public List<MemberDto> selectMobileCarrierGroup() {
		return memberDao.selectMobileCarrierGroup();
	}

	@PostConstruct
	public void selectListCachedCodeArrayList() throws Exception {
		List<MemberDto> codeListFromDb = (ArrayList<MemberDto>) memberDao.selectListCachedCodeArrayList();
		MemberDto.cachedCodeArrayList.clear(); 
		MemberDto.cachedCodeArrayList.addAll(codeListFromDb);
		System.out.println("cachedCodeArrayList: " + MemberDto.cachedCodeArrayList.size() + " chached !");
	}
    
    
	public static void clear() throws Exception {
		MemberDto.cachedCodeArrayList.clear();
	}
	
	
	public static List<MemberDto> selectListCachedCode(String seq) throws Exception {
		List<MemberDto> rt = new ArrayList<MemberDto>();
		for(MemberDto codeRow : MemberDto.cachedCodeArrayList) {
			if (codeRow.getSeq().equals(seq)) {
				rt.add(codeRow);
			} else {
				// by pass
			}
		}
		return rt;
	}

	
	public static String selectOneCachedCode(int code) throws Exception {
		String rt = "";
		for(MemberDto codeRow : MemberDto.cachedCodeArrayList) {
			if (codeRow.getGender() == code) {
				if (codeRow.getGender() == 1) {
					rt = "남";
				} else if (codeRow.getGender() == 2) {
					rt = "여";
				} else {
					rt = "공개안함";
				}
				break;

			} else {
				// by pass
			}
		}
		return rt;
	}
}
