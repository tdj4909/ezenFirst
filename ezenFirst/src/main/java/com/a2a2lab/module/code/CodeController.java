package com.a2a2lab.module.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.a2a2lab.module.codeGroup.CodeGroupService;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Controller
public class CodeController {

	@Autowired
	CodeService service;
	
	@Autowired
	CodeGroupService codeGroupService;
	
	// 코드 관리 화면
	@RequestMapping("/xdm/system/code/list")
	public String showCodeManagement(Model model, PageVo pageVo, SearchVo searchVo) {
		// 검색 설정
		model.addAttribute("searchVo", searchVo);
		// 페이징 설정
		pageVo.setParamsPaging(service.countCodesByVo(pageVo, searchVo));
		model.addAttribute("pageVo", pageVo);
		// 코드 출력
		model.addAttribute("list", service.findCodesByVo(pageVo, searchVo));
		return "xdm/code/codeList";
	}
	// 코드 등록/수정 화면
	@RequestMapping("/xdm/system/code/edit")
	public String showCodeEdit(Model model, @RequestParam("codeId") String codeId){
		model.addAttribute("codegroupList", codeGroupService.findAllCodeGroup());
		// codeId가 있으면 수정, 없으면 등록
		if (!codeId.equals("") && !codeId.equals("0")) {
			model.addAttribute("item", service.findCodeById(codeId));
		}
		return "xdm/code/codeEdit";
	}
	// 코드 추가
	@RequestMapping("/xdm/system/code/create")
	public String createCode(CodeDto dto) {
		service.createCode(dto);
		return "redirect:/xdm/system/code/list";
	}
	// 코드 수정
	@RequestMapping("/xdm/system/code/update")
	public String updateCode(CodeDto dto) {
		service.updateCode(dto);
		return "redirect:/xdm/system/code/list";
	}
	// 코드 softDelete
	@RequestMapping("/xdm/system/code/softDelete")
	public String softDeleteCode(@RequestParam("delSeq") List<String> idList) {
		for(String id : idList) {
			if(!id.equals("")) {
				service.softDeleteCode(id);
			}
		}
		return "redirect:/xdm/system/code/list";
	}
	// 코드 hardDelete
	@RequestMapping("/xdm/system/code/hardDelete")
	public String hardDeleteCode(@RequestParam("delSeq") List<String> idList) {
		for(String id : idList) {
			if(!id.equals("")) {
				service.hardDeleteCode(id);
			}
		}
		return "redirect:/xdm/system/code/list";
	}
}