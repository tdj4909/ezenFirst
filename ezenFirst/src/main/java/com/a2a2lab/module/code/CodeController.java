package com.a2a2lab.module.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.a2a2lab.module.codeGroup.CodeGroupService;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Controller
public class CodeController {

	@Autowired
	CodeService service;
	
	@Autowired
	CodeGroupService codeGroupService;
	
	// 코드 관리 화면
	@RequestMapping("/xdm/system/code/list")
	public String showCodeManagement(Model model, PageVo pageVo, SearchVo searchVo) {

		// 검색 설정
		model.addAttribute("searchVo", searchVo);
		// 페이징 설정
		pageVo.setParamsPaging(service.countCodesByVo(pageVo, searchVo));
		model.addAttribute("pageVo", pageVo);
		
		model.addAttribute("list", service.findCodesByVo(pageVo, searchVo));
		
		return "xdm/code/codeList";
	}
	
	// 코드 등록/수정 화면
	@RequestMapping("/xdm/system/code/edit")
	public String showCodeEdit(Model model, @RequestParam("codeId") String codeId){
		
		model.addAttribute("codegroupList", codeGroupService.findAllCodeGroup());
		// codeId가 있으면 수정, 없으면 등록
		if (!codeId.equals("") && !codeId.equals("0")) {
			model.addAttribute("item", service.findCodeById(codeId));
		}
		
		return "xdm/code/codeEdit";
	}
	
	// 코드 추가
	@RequestMapping("/xdm/system/code/create")
	public String createCode(CodeDto dto) {
		service.createCode(dto);
		return "redirect:/xdm/system/code/list";
	}
	
	// 코드 수정
	@RequestMapping("/xdm/system/code/update")
	public String updateCode(CodeDto dto) {
		service.updateCode(dto);
		return "redirect:/xdm/system/code/list";
	}
	
	// 코드 softDelete
	@RequestMapping("/xdm/system/code/softDelete")
	public String softDeleteCode(@RequestParam("delSeq") List<String> idList) {

		for(String id : idList) {
			if(!id.equals("")) {
				service.softDeleteCode(id);
			}
		}
		
		return "redirect:/xdm/system/code/list";
	}
	
	// 코드 hardDelete
	@RequestMapping("/xdm/system/code/hardDelete")
	public String hardDeleteCode(@RequestParam("delSeq") List<String> idList) {

		for(String id : idList) {
			if(!id.equals("")) {
				service.hardDeleteCode(id);
			}
		}
		
		return "redirect:/xdm/system/code/list";
	}
	
	
	
	
	
	
	
	
	
	
//	@RequestMapping(value = "/Xdm/codeXdmList")
//	public String codeXdmList(@ModelAttribute("vo") CodeVo vo, Model model, HttpSession httpSession) {
//		
//		// login 검사
//		if(httpSession.getAttribute("user") == null) {
//			return "redirect:/Xdm/loginXdm";
//		}
//		model.addAttribute("user", httpSession.getAttribute("user"));
//		
////		setSearch(vo);
//		vo.setParamsPaging(codeService.selectOneCount(vo));
//		if (vo.getTotalRows() > 0) {
//			model.addAttribute("lists", codeService.selectList(vo));
//		}
//		
//		
//		return "/xdm/code/codeXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/codeXdmRegister")
//	public String codeXdmRegister(@ModelAttribute("vo") CodeVo vo, Model model, HttpSession httpSession) {
//		
//		// login 검사
//		if(httpSession.getAttribute("user") == null) {
//			return "redirect:/Xdm/loginXdm";
//		}
//		model.addAttribute("user", httpSession.getAttribute("user"));
//		
//		model.addAttribute("codeGroup", codeService.selectCodeGroup());
//		if (vo.getIfcgSeq().equals("0") || vo.getIfcgSeq().equals("")) {
////			insert mode
//		} else {
////			update mode
//			model.addAttribute("item", codeService.selectOne(vo));
//		}
//		return "/xdm/code/codeXdmRegister";
//	}
//	
//	@RequestMapping(value = "/Xdm/codeXdmInst")
//	public String codeXdmInst(CodeDto codeDto) {
//		codeService.insert(codeDto);
//		return "redirect:/Xdm/codeXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/codeXdmUpdt")
//	public String codeXdmUpdt(CodeDto codeDto) {
//		codeService.update(codeDto);
//		return "redirect:/Xdm/codeXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/codeXdmUele")
//	public String codeGroupXdmUele(@RequestParam("delSeq") List<String> delSeq) {
//
//		for(String delseq : delSeq) {
//			if(!delseq.equals("")) {
//				CodeDto codeDto = new CodeDto();
//				codeDto.setIfcgSeq(delseq);
//				codeService.uelete(codeDto);
//			}
//		}
//		return "redirect:/Xdm/codeXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/codeXdmDele")
//	public String codeGroupXdmDele(@RequestParam("delSeq") List<String> delSeq) {
//		
//		for(String delseq : delSeq) {
//			if(!delseq.equals("")) {
//				CodeDto codeDto = new CodeDto();
//				codeDto.setIfcgSeq(delseq);
//				codeService.delete(codeDto);
//			}
//		}
//		return "redirect:/Xdm/codeXdmList";
//	}
	
}