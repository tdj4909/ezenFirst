package com.a2a2lab.module.codeGroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class CodeGroupController {

	@Autowired
	CodeGroupService codeGroupService;
	
	@RequestMapping(value = "/Xdm/codeGroupXdmList")
	public String codeGroupXdmList(@ModelAttribute("vo") CodeGroupVo vo, Model model, HttpSession httpSession) throws Exception {
		
		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/Xdm/loginXdm";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
		
//		setSearch(vo);
		System.out.println(vo);
		vo.setParamsPaging(codeGroupService.selectOneCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("lists", codeGroupService.selectList(vo));
		}
		
		
		return "/xdm/codeGroup/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/Xdm/codeGroupXdmInst")
	public String codeGroupXdmInst(CodeGroupDto codeGroupDto) {
		codeGroupService.insert(codeGroupDto);
		return "redirect:/Xdm/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/Xdm/codeGroupXdmUpdt")
	public String codeGroupXdmUpdt(CodeGroupDto codeGroupDto) {
		codeGroupService.update(codeGroupDto);
		return "redirect:/Xdm/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/Xdm/codeGroupXdmDele")
	public String codeGroupXdmDele(@RequestParam("delSeq") List<String> delSeq) {
		
		for(String delseq : delSeq) {
			if(!delseq.equals("")) {
				CodeGroupDto codeGroupDto = new CodeGroupDto();
				codeGroupDto.setIfcgSeq(delseq);
				codeGroupService.delete(codeGroupDto);
			}
		}
		return "redirect:/Xdm/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/Xdm/codeGroupXdmUele")
	public String codeGroupXdmUele(@RequestParam("delSeq") List<String> delSeq) {

		for(String delseq : delSeq) {
			if(!delseq.equals("")) {
				CodeGroupDto codeGroupDto = new CodeGroupDto();
				codeGroupDto.setIfcgSeq(delseq);
				codeGroupService.uelete(codeGroupDto);
			}
		}
		return "redirect:/Xdm/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/Xdm/usrIndex")
	public String usrIndex() {
		return "/usr/index/index";
	}
	
	@RequestMapping(value = "/Xdm/codeGroupXdmRegister")
	public String codeGroupXdmRegister(@ModelAttribute("vo") CodeGroupVo vo, Model model, HttpSession httpSession) throws Exception{
		
		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/Xdm/loginXdm";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
		
		if (vo.getIfcgSeq().equals("0") || vo.getIfcgSeq().equals("")) {
//			insert mode
		} else {
//			update mode
			model.addAttribute("item", codeGroupService.selectOne(vo));
		}
		return "/xdm/codeGroup/codeGroupXdmRegister";
	}
	
}