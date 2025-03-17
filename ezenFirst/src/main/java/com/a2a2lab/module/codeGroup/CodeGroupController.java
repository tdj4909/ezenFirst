package com.a2a2lab.module.codeGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeGroupController {

	@Autowired
	CodeGroupService codeGroupService;
	
	@RequestMapping(value = "/codeGroupXdmList")
	public String codeGroupXdmList(CodeGroupVo vo, Model model) {
		
		vo.setParamsPaging(codeGroupService.selectOneCount());
		model.addAttribute("list", codeGroupService.selectList(vo));
		model.addAttribute("vo", vo);
		
		return "/xdm/codeGroup/codeGroupXdmList";
	}
	
	
	@RequestMapping(value = "/codeGroupXdmRegister")
	public String codeGroupXdmRegister() {

		return "/xdm/codeGroup/codeGroupXdmRegister";
	}
	
	@RequestMapping(value = "/codeGroupXdmInst")
	public String codeGroupXdmInst(CodeGroupDto codeGroupDto) {
		codeGroupService.insert(codeGroupDto);
		return "redirect:/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/codeGroupXdmDele")
	public String codeGroupXdmDele(CodeGroupDto codeGroupDto) {
		codeGroupService.delete(codeGroupDto);
		return "redirect:/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/codeGroupXdmUele")
	public String codeGroupXdmUele(CodeGroupDto codeGroupDto) {
		codeGroupService.uelete(codeGroupDto);
		return "redirect:/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/usrIndex")
	public String usrIndex() {
		return "/usr/index/index";
	}
	
}