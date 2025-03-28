package com.a2a2lab.module.account;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.codeGroup.CodeGroupDto;
import com.a2a2lab.module.codeGroup.CodeGroupVo;

import jakarta.annotation.PostConstruct;

@Service
public class AccountService {
	/*
	 * @Autowired AccountDao dao;
	 * 
	 * public int insert(AccountDto dto) { return dao.insert(dto); }
	 * 
	 * public int update(AccountDto dto) { return dao.update(dto); }
	 * 
	 * public int uelete(AccountDto dto) { return dao.uelete(dto); }
	 * 
	 * public int selectOneCount(AccountVo vo) { return dao.selectOneCount(vo); }
	 * 
	 * public AccountDto selectOne(AccountVo vo) { return dao.selectOne(vo); }
	 * 
	 * public AccountDto loginChk(AccountDto dto) { return dao.loginChk(dto); }
	 * 
	 * public List<AccountDto> selectList(AccountVo vo) { return dao.selectList(vo);
	 * }
	 * 
	 * public List<AccountDto> selectMobileCarrierGroup() { return
	 * dao.selectMobileCarrierGroup(); }
	 * 
	 * @PostConstruct public void selectListCachedCodeArrayList() throws Exception {
	 * List<AccountDto> codeListFromDb = (ArrayList<AccountDto>)
	 * dao.selectListCachedCodeArrayList(); AccountDto.cachedCodeArrayList.clear();
	 * AccountDto.cachedCodeArrayList.addAll(codeListFromDb);
	 * System.out.println("cachedCodeArrayList: " +
	 * AccountDto.cachedCodeArrayList.size() + " chached !"); }
	 * 
	 * 
	 * public static void clear() throws Exception {
	 * AccountDto.cachedCodeArrayList.clear(); }
	 * 
	 * 
	 * public static List<AccountDto> selectListCachedCode(String seq) throws
	 * Exception { List<AccountDto> rt = new ArrayList<AccountDto>(); for(AccountDto
	 * codeRow : AccountDto.cachedCodeArrayList) { if (codeRow.getSeq().equals(seq))
	 * { rt.add(codeRow); } else { // by pass } } return rt; }
	 * 
	 * 
	 * public static String selectOneCachedCode(int code) throws Exception { String
	 * rt = ""; for(AccountDto codeRow : AccountDto.cachedCodeArrayList) { if
	 * (codeRow.getGender() == code) { if (codeRow.getGender() == 1) { rt = "남"; }
	 * else if (codeRow.getGender() == 2) { rt = "여"; } else { rt = "공개안함"; } break;
	 * 
	 * } else { // by pass } } return rt; }
	 */
}
