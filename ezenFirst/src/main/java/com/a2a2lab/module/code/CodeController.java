package com.a2a2lab.module.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class CodeController {

	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "/Xdm/codeXdmList")
	public String codeXdmList(@ModelAttribute("vo") CodeVo vo, Model model, HttpSession httpSession) {
		
		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/Xdm/loginXdm";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
		
//		setSearch(vo);
		vo.setParamsPaging(codeService.selectOneCount(vo));
		if (vo.getTotalRows() > 0) {
			model.addAttribute("lists", codeService.selectList(vo));
		}
		
		
		return "/xdm/code/codeXdmList";
	}
	
	@RequestMapping(value = "/Xdm/codeXdmRegister")
	public String codeXdmRegister(@ModelAttribute("vo") CodeVo vo, Model model, HttpSession httpSession) {
		
		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/Xdm/loginXdm";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
		
		model.addAttribute("codeGroup", codeService.selectCodeGroup());
		if (vo.getIfcgSeq().equals("0") || vo.getIfcgSeq().equals("")) {
//			insert mode
		} else {
//			update mode
			model.addAttribute("item", codeService.selectOne(vo));
		}
		return "/xdm/code/codeXdmRegister";
	}
	
	@RequestMapping(value = "/Xdm/codeXdmInst")
	public String codeXdmInst(CodeDto codeDto) {
		codeService.insert(codeDto);
		return "redirect:/Xdm/codeXdmList";
	}
	
	@RequestMapping(value = "/Xdm/codeXdmUpdt")
	public String codeXdmUpdt(CodeDto codeDto) {
		codeService.update(codeDto);
		return "redirect:/Xdm/codeXdmList";
	}
	
	@RequestMapping(value = "/Xdm/codeXdmUele")
	public String codeGroupXdmUele(@RequestParam("delSeq") List<String> delSeq) {

		for(String delseq : delSeq) {
			if(!delseq.equals("")) {
				CodeDto codeDto = new CodeDto();
				codeDto.setIfcgSeq(delseq);
				codeService.uelete(codeDto);
			}
		}
		return "redirect:/Xdm/codeXdmList";
	}
	
	@RequestMapping(value = "/Xdm/codeXdmDele")
	public String codeGroupXdmDele(@RequestParam("delSeq") List<String> delSeq) {
		
		for(String delseq : delSeq) {
			if(!delseq.equals("")) {
				CodeDto codeDto = new CodeDto();
				codeDto.setIfcgSeq(delseq);
				codeService.delete(codeDto);
			}
		}
		return "redirect:/Xdm/codeXdmList";
	}
	
}