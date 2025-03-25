package com.a2a2lab.module.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.a2a2lab.module.codeGroup.CodeGroupDto;
import com.a2a2lab.module.codeGroup.CodeGroupVo;

@Controller
public class CodeController {

	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "/codeXdmList")
	public String codeXdmList(@ModelAttribute("vo") CodeVo vo, Model model) {
		
//		setSearch(vo);
		vo.setParamsPaging(codeService.selectOneCount(vo));
		if (vo.getTotalRows() > 0) {
			model.addAttribute("lists", codeService.selectList(vo));
		}
		
		
		return "/xdm/code/codeXdmList";
	}
	
	@RequestMapping(value = "/codeXdmRegister")
	public String codeXdmRegister(@ModelAttribute("vo") CodeVo vo, Model model) {
		model.addAttribute("codeGroup", codeService.selectCodeGroup());
		if (vo.getIfcgSeq().equals("0") || vo.getIfcgSeq().equals("")) {
//			insert mode
		} else {
//			update mode
			model.addAttribute("item", codeService.selectOne(vo));
		}
		return "/xdm/code/codeXdmRegister";
	}
	
	@RequestMapping(value = "/codeXdmInst")
	public String codeXdmInst(CodeDto codeDto) {
		codeService.insert(codeDto);
		return "redirect:/codeXdmList";
	}
	
	@RequestMapping(value = "/codeXdmUpdt")
	public String codeXdmUpdt(CodeDto codeDto) {
		codeService.update(codeDto);
		return "redirect:/codeXdmList";
	}
	
}