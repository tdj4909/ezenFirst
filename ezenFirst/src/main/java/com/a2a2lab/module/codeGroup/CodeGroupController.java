package com.a2a2lab.module.codeGroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class CodeGroupController {

	@Autowired
	CodeGroupService service;
	
	// 코드 그룹 관리 화면
	@RequestMapping(value = "/xdm/system/codeGroup/list")
	public String showCodeGroupManagement(Model model, PageVo pageVo, SearchVo searchVo) {

		// 검색 설정
		model.addAttribute("searchVo", searchVo);
		// 페이징 설정
		pageVo.setParamsPaging(service.countCodeGroupsByVo(searchVo));
		model.addAttribute("pageVo", pageVo);
		
		
		model.addAttribute("list", service.findCodeGroupsByVo(pageVo, searchVo));
		
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