package com.a2a2lab.module.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.a2a2lab.module.codeGroup.CodeGroupDto;

@Controller
public class MemberController {

	@Autowired
	MemberService codeService;
	
	@RequestMapping(value = "/codeXdmList")
	public String codeXdmList(MemberVo vo, Model model) {
		
		vo.setParamsPaging(codeService.selectOneCount());
		model.addAttribute("list", codeService.selectList(vo));
		model.addAttribute("vo", vo);
		
		
		return "/xdm/code/codeXdmList";
	}
	
	@RequestMapping(value = "/codeXdmRegister")
	public String codeXdmRegister(Model model) {
		model.addAttribute("codeGroup", codeService.selectCodeGroup());
		return "/xdm/code/codeXdmRegister";
	}
	
	@RequestMapping(value = "/codeXdmInst")
	public String codeXdmInst(MemberDto codeDto) {
		codeService.insert(codeDto);
		return "redirect:/codeXdmList";
	}
	
}