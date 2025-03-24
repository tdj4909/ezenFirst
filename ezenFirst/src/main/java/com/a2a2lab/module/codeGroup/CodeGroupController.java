package com.a2a2lab.module.codeGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeGroupController {

	@Autowired
	CodeGroupService codeGroupService;
	
	@RequestMapping(value = "/codeGroupXdmList")
	public String codeGroupXdmList(@ModelAttribute("vo") CodeGroupVo vo, Model model) throws Exception {
		
//		setSearch(vo);
		vo.setParamsPaging(codeGroupService.selectOneCount(vo));
		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", codeGroupService.selectList(vo));
		}
		
		
		System.out.println("@@@@@@@@@@@@@@@@@@");
		System.out.println(vo.getShDelNy());
		System.out.println(vo.getShUseNy());
		System.out.println(vo.getShDateStart());
		System.out.println(vo.getShDateEnd());
		
		
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