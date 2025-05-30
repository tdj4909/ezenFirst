package com.a2a2lab.module.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.cart.CartDao;
import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.code.CodeService;
import com.a2a2lab.module.order.OrderDao;
import com.a2a2lab.module.review.ReviewDao;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Service
public class MemberService {

	@Autowired
	MemberDao dao;
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	ReviewDao reviewDao;
	
	public int countMembersByVo(SearchVo searchVo) {
		return dao.countMembersByVo(searchVo);
	}
	
	public List<MemberDto> findMembersByVo(PageVo pageVo, SearchVo searchVo) {
		// 캐싱된 코드 맵에서 코드그룹 가져오기
	    List<CodeDto> mobileCarrierCodeDtos = codeService.getCodesByCodegroupName("통신사");
	    List<CodeDto> genderCodeDtos = codeService.getCodesByCodegroupName("성별");

	    // codeId → name 맵으로 변환
	    Map<Integer, String> codeNameMap = new HashMap<>();
	    for (CodeDto codeDto : mobileCarrierCodeDtos) {
	        codeNameMap.put(Integer.parseInt(codeDto.getCodeId()), codeDto.getName());
	    }
	    for (CodeDto codeDto : genderCodeDtos) {
	        codeNameMap.put(Integer.parseInt(codeDto.getCodeId()), codeDto.getName());
	    }

	    // 회원 목록 가져오기
	    List<MemberDto> dtos = dao.findMembersByVo(pageVo, searchVo);

	    // 각 회원의 통신사, 성별 코드에 해당하는 이름 설정
	    for (MemberDto dto : dtos) {
	        dto.setMobileCarrierName(codeNameMap.get(dto.getMobileCarrier()));
	        dto.setGenderName(codeNameMap.get(dto.getGender()));
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
		cartDao.softDeleteCartByMemberId(id);
		orderDao.softDeleteOrderMasterByMemberId(id);
		reviewDao.softDeleteReviewByMemberId(id);
		
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

}
