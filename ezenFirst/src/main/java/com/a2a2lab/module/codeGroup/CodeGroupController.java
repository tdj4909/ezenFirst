package com.a2a2lab.module.codeGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeGroupController {

	@Autowired
	CodeGroupService codeGroupService;
	
	@RequestMapping(value = "/codeGroup")
	public String codeGroup(Model model) {
		model.addAttribute("list", codeGroupService.selectList());
		return "/xdm/codeGroup/codeGroupXdmList";
	}
	
//	@RequestMapping(value = "/codeGroupList")
//	public String codeGroupList(Model model) {
//		model.addAttribute("list", codeGroupService.selectList());
//		return "redirect:/codeGroup";
//	}
	
	@RequestMapping(value = "/codeGroupRegister")
	public String codeGroupRegister() {

		return "/xdm/codeGroup/codeGroupXdmRegister";
	}
	
	
}
