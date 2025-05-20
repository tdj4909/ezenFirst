package com.a2a2lab.module.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.a2a2lab.module.code.CodeDao;
import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Service
public class MemberService {

	@Autowired
	MemberDao dao;
	
	@Autowired
	CodeDao codeDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public int countMembersByVo(PageVo pageVo, SearchVo searchVo) {
		return dao.countMembersByVo(pageVo, searchVo);
	}
	
	public List<MemberDto> findMembersByVo(PageVo pageVo, SearchVo searchVo) {
		// 통신사 이름 세팅
		Map<Integer, String> map = new HashMap<>();
		List<CodeDto> codeDtos= codeDao.findCodesByCodeGroupId("1");
		for(CodeDto codeDto : codeDtos) {
			map.put(Integer.parseInt(codeDto.getCodeId()), codeDto.getName());
		}
		
		List<MemberDto> dtos = dao.findMembersByVo(pageVo, searchVo);
		for(MemberDto dto : dtos) {
			dto.setMobileCarrierName(map.get(dto.getMobileCarrier()));
		}
		return dtos;
	}
	
	public MemberDto findMemberById(String memberId) {
		return dao.findMemberById(memberId);
	}
	
	public MemberDto findMemberByEmail(String email) {
		return dao.findMemberByEmail(email);
	}
	
	public int countAllMember() {
		return dao.countAllMember();
	}
	
	public int createMember(MemberDto dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return dao.createMember(dto);
	}
	
	public int updateMember(MemberDto dto) {
		return dao.updateMember(dto);
	}
	
	public int softDeleteMember(String id) {
		return dao.softDeleteMember(id);
	}
	
	public int hardDeleteMember(String id) {
		return dao.hardDeleteMember(id);
	}
	
	public int emailChk(String email) {
		return dao.emailChk(email);
	}
	
	public boolean changePassword(String email, String currentPassword, String newPassword) {
		MemberDto dto = dao.findMemberByEmail(email);
		// 현재 비밀번호 확인
	    if (!passwordEncoder.matches(currentPassword, dto.getPassword())) {
	        throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
	    }
	    // 새 비밀번호 암호화 후 저장
	    dto.setPassword(passwordEncoder.encode(newPassword));
	    dao.changePwd(dto);
		
		return true;
	}
	
	
	
//	public int insert(MemberDto dto) {
//		return dao.insert(dto);
//	}
//	
//	public int update(MemberDto dto) {
//		return dao.update(dto);
//	}
//	
//	public int uelete(MemberDto dto) {
//		return dao.uelete(dto);
//	}
//	
//	public int delete(MemberDto dto) {
//		return dao.delete(dto);
//	}
//
//	public int memberCount() {
//		return dao.memberCount();
//	}
//	
//	public int selectOneCount(MemberVo vo) {
//		return dao.selectOneCount(vo);
//	}
//	
//	public MemberDto selectOne(MemberVo vo) {
//		return dao.selectOne(vo);
//	}
//	
//	public MemberDto loginChk(MemberDto dto) {
//		return dao.loginChk(dto);
//	}
//	
//	public int emailChk(MemberDto dto) {
//		return dao.emailChk(dto);
//	}
//
//	public List<MemberDto> selectList(MemberVo vo) {
//		return dao.selectList(vo);
//	}
//	
//	public List<MemberDto> selectMobileCarrierGroup() {
//		return dao.selectMobileCarrierGroup();
//	}
//
//	@PostConstruct
//	public void selectListCachedCodeArrayList() throws Exception {
//		List<MemberDto> codeListFromDb = (ArrayList<MemberDto>) dao.selectListCachedCodeArrayList();
//		MemberDto.cachedCodeArrayList.clear(); 
//		MemberDto.cachedCodeArrayList.addAll(codeListFromDb);
//		System.out.println("cachedCodeArrayList: " + MemberDto.cachedCodeArrayList.size() + " chached !");
//	}
//    
//    
//	public static void clear() throws Exception {
//		MemberDto.cachedCodeArrayList.clear();
//	}
//	
//	
//	public static List<MemberDto> selectListCachedCode(String seq) throws Exception {
//		List<MemberDto> rt = new ArrayList<MemberDto>();
//		for(MemberDto codeRow : MemberDto.cachedCodeArrayList) {
//			if (codeRow.getMemberId().equals(seq)) {
//				rt.add(codeRow);
//			} else {
//				// by pass
//			}
//		}
//		return rt;
//	}
//
//	
//	public static String selectOneCachedCode(int code) throws Exception {
//		String rt = "";
//		for(MemberDto codeRow : MemberDto.cachedCodeArrayList) {
//			if (codeRow.getGender() == code) {
//				if (codeRow.getGender() == 1) {
//					rt = "남";
//				} else if (codeRow.getGender() == 2) {
//					rt = "여";
//				} else {
//					rt = "공개안함";
//				}
//				break;
//
//			} else {
//				// by pass
//			}
//		}
//		return rt;
//	}
//	
////	암호화
//	public String encodeBcrypt(String planeText, int strength) {
//		  return new BCryptPasswordEncoder(strength).encode(planeText);
//	}
//
//			
//	public boolean matchesBcrypt(String planeText, String hashValue, int strength) {
//	  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
//	  return passwordEncoder.matches(planeText, hashValue);
//	}
}
