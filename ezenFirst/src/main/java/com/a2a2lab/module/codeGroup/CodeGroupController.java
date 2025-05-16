package com.a2a2lab.module.codeGroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Controller
public class CodeGroupController {

	@Autowired
	CodeGroupService service;
	
	// 코드그룹 관리 화면
	@RequestMapping("/xdm/system/codegroup/list")
	public String showCodeGroupManagement(Model model, PageVo pageVo, SearchVo searchVo) {

		// 검색 설정
		model.addAttribute("searchVo", searchVo);
		// 페이징 설정
		pageVo.setParamsPaging(service.countCodeGroupsByVo(pageVo, searchVo));
		model.addAttribute("pageVo", pageVo);
		
		model.addAttribute("list", service.findCodeGroupsByVo(pageVo, searchVo));
		
		return "xdm/codegroup/codeGroupList";
	}
	
	// 코드그룹 등록/수정 화면
	@RequestMapping("/xdm/system/codegroup/edit")
	public String showCodeGroupEdit(Model model, @RequestParam("codegroupId") String codegroupId){
		
		// codegroupId가 있으면 수정, 없으면 등록
		if (!codegroupId.equals("") && !codegroupId.equals("0")) {
			model.addAttribute("item", service.findCodeGroupById(codegroupId));
		}
		
		return "xdm/codegroup/codeGroupEdit";
	}
	
	// 코드그룹 추가
	@RequestMapping("/xdm/system/codegroup/create")
	public String createCodeGroup(CodeGroupDto dto) {
		service.createCodeGroup(dto);
		return "redirect:/xdm/system/codegroup/list";
	}
	
	// 코드그룹 수정
	@RequestMapping("/xdm/system/codegroup/update")
	public String updateCodeGroup(CodeGroupDto dto) {
		service.updateCodeGroup(dto);
		return "redirect:/xdm/system/codegroup/list";
	}
	
	// 코드그룹 softDelete
	@RequestMapping("/xdm/system/codegroup/softDelete")
	public String softDeleteCodeGroup(@RequestParam("delSeq") List<String> codegroupIdList) {

		for(String codegroupId : codegroupIdList) {
			if(!codegroupId.equals("")) {
				service.softDeleteCodeGroup(codegroupId);
			}
		}
		
		return "redirect:/xdm/system/codegroup/list";
	}
	
	// 코드그룹 hardDelete
	@RequestMapping("/xdm/system/codegroup/hardDelete")
	public String hardDeleteCodeGroup(@RequestParam("delSeq") List<String> codegroupIdList) {

		for(String codegroupId : codegroupIdList) {
			if(!codegroupId.equals("")) {
				service.hardDeleteCodeGroup(codegroupId);
			}
		}
		
		return "redirect:/xdm/system/codegroup/list";
	}
	
}