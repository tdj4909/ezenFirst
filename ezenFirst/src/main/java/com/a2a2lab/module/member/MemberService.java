package com.a2a2lab.module.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.codeGroup.CodeGroupDto;
import com.a2a2lab.module.codeGroup.CodeGroupVo;

import jakarta.annotation.PostConstruct;

@Service
public class MemberService {

	@Autowired
	MemberDao dao;
	
	public int insert(MemberDto dto) {
		return dao.insert(dto);
	}
	
	public int update(MemberDto dto) {
		return dao.update(dto);
	}
	
	public int uelete(MemberDto dto) {
		return dao.uelete(dto);
	}
	
	public int delete(MemberDto dto) {
		return dao.delete(dto);
	}

	public int memberCount() {
		return dao.memberCount();
	}
	
	public int selectOneCount(MemberVo vo) {
		return dao.selectOneCount(vo);
	}
	
	public MemberDto selectOne(MemberVo vo) {
		return dao.selectOne(vo);
	}
	
	public MemberDto loginChk(MemberDto dto) {
		return dao.loginChk(dto);
	}
	
	public int emailChk(MemberDto dto) {
		return dao.emailChk(dto);
	}

	public List<MemberDto> selectList(MemberVo vo) {
		return dao.selectList(vo);
	}
	
	public List<MemberDto> selectMobileCarrierGroup() {
		return dao.selectMobileCarrierGroup();
	}

	@PostConstruct
	public void selectListCachedCodeArrayList() throws Exception {
		List<MemberDto> codeListFromDb = (ArrayList<MemberDto>) dao.selectListCachedCodeArrayList();
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
	
//	암호화
	public String encodeBcrypt(String planeText, int strength) {
		  return new BCryptPasswordEncoder(strength).encode(planeText);
	}

			
	public boolean matchesBcrypt(String planeText, String hashValue, int strength) {
	  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
	  return passwordEncoder.matches(planeText, hashValue);
	}
}
