package com.a2a2lab.module.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeController {

	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "/codeXdmList")
	public String codeXdmList(CodeVo vo, Model model) {
		
		vo.setParamsPaging(codeService.selectOneCount());
		model.addAttribute("list", codeService.selectList(vo));
		model.addAttribute("vo", vo);
		
		return "/xdm/code/codeXdmList";
	}
	
	@RequestMapping(value = "/codeXdmRegister")
	public String codeXdmRegister() {

		return "/xdm/code/codeXdmRegister";
	}
	
}