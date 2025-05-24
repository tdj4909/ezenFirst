package com.a2a2lab.module.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

import jakarta.annotation.PostConstruct;

@Service
public class CodeService {

	@Autowired
	CodeDao dao;
	
	private Map<String, List<CodeDto>> codeMap;
	
//	****************************************
//	관리자 화면
//	****************************************
	// 검색한 코드 총 개수
	public int countCodesByVo(PageVo pageVo, SearchVo searchVo) {
		return dao.countCodesByVo(pageVo, searchVo);
	}
	// 검색한 코드
	public List<CodeDto> findCodesByVo(PageVo pageVo, SearchVo searchVo) {
		return dao.findCodesByVo(pageVo, searchVo);
	}
	// 코드 수정용
	public CodeDto findCodeById(String id) {
		return dao.findCodeById(id);
	}
	// 캐시로 대체
	public List<CodeDto> findCodesByCodeGroupId(String codegroupId) {
		return dao.findCodesByCodeGroupId(codegroupId);
	}
	// 코드 관리
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
	
	// 캐싱
    @PostConstruct
    public void loadCodeCache() {
        List<CodeDto> codes = dao.findAllCodes();

        codeMap = new HashMap<>();

        for (CodeDto code : codes) {
            String codegroupName = code.getCodegroupName();
            if (!codeMap.containsKey(codegroupName)) {
                codeMap.put(codegroupName, new ArrayList<>());
            }
            codeMap.get(codegroupName).add(code);
        }
    }

    public List<CodeDto> getCodesByCodegroupName(String codegroupName) {
        return codeMap.getOrDefault(codegroupName, Collections.emptyList());
    }

}
