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
	
	// 코드그룹 관리 화면
	@RequestMapping("/xdm/system/codeGroup/list")
	public String showCodeGroupManagement(Model model, PageVo pageVo, SearchVo searchVo) {

		// 검색 설정
		model.addAttribute("searchVo", searchVo);
		// 페이징 설정
		pageVo.setParamsPaging(service.countCodeGroupsByVo(pageVo, searchVo));
		model.addAttribute("pageVo", pageVo);
		
		model.addAttribute("list", service.findCodeGroupsByVo(pageVo, searchVo));
		
		return "/xdm/codeGroup/codeGroupList";
	}
	
	// 코드그룹 등록/수정 화면
	@RequestMapping("/xdm/system/codeGroup/edit")
	public String showCodeGroupEdit(Model model, @RequestParam("codegroupId") String codegroupId){
		
		// codegroupId가 있으면 수정, 없으면 등록
		if (!codegroupId.equals("") && !codegroupId.equals("0")) {
			model.addAttribute("item", service.findCodeGroupById(codegroupId));
		}
		
		return "/xdm/codeGroup/codeGroupEdit";
	}
	
	// 코드그룹 추가
	@RequestMapping("/xdm/system/codeGroup/create")
	public String createCodeGroup(CodeGroupDto codeGroupDto) {
		service.createCodeGroup(codeGroupDto);
		return "redirect:/xdm/system/codeGroup/list";
	}
	
	// 코드그룹 수정
	@RequestMapping("/xdm/system/codeGroup/update")
	public String updateCodeGroup(CodeGroupDto codeGroupDto) {
		service.updateCodeGroup(codeGroupDto);
		return "redirect:/xdm/system/codeGroup/list";
	}
	
	// 코드그룹 softDelete
	@RequestMapping(value = "/xdm/system/codeGroup/softDelete")
	public String softDeleteCodeGroup(@RequestParam("delSeq") List<String> codegroupIdList) {

		for(String codegroupId : codegroupIdList) {
			if(!codegroupId.equals("")) {
				service.softDeleteCodeGroup(codegroupId);
			}
		}
		
		return "redirect:/xdm/system/codeGroup/list";
	}
	
	// 코드그룹 hardDelete
	@RequestMapping(value = "/xdm/system/codeGroup/hardDelete")
	public String hardDeleteCodeGroup(@RequestParam("delSeq") List<String> codegroupIdList) {

		for(String codegroupId : codegroupIdList) {
			if(!codegroupId.equals("")) {
				service.hardDeleteCodeGroup(codegroupId);
			}
		}
		
		return "redirect:/xdm/system/codeGroup/list";
	}
	
	
	
	
	
//	
//	@RequestMapping(value = "/Xdm/codeGroupXdmInst")
//	public String codeGroupXdmInst(CodeGroupDto codeGroupDto) {
//		service.insert(codeGroupDto);
//		return "redirect:/Xdm/codeGroupXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/codeGroupXdmUpdt")
//	public String codeGroupXdmUpdt(CodeGroupDto codeGroupDto) {
//		service.update(codeGroupDto);
//		return "redirect:/Xdm/codeGroupXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/codeGroupXdmDele")
//	public String codeGroupXdmDele(@RequestParam("delSeq") List<String> delSeq) {
//		
//		for(String delseq : delSeq) {
//			if(!delseq.equals("")) {
//				CodeGroupDto codeGroupDto = new CodeGroupDto();
//				codeGroupDto.setIfcgSeq(delseq);
//				service.delete(codeGroupDto);
//			}
//		}
//		return "redirect:/Xdm/codeGroupXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/codeGroupXdmUele")
//	public String codeGroupXdmUele(@RequestParam("delSeq") List<String> delSeq) {
//
//		for(String delseq : delSeq) {
//			if(!delseq.equals("")) {
//				CodeGroupDto codeGroupDto = new CodeGroupDto();
//				codeGroupDto.setIfcgSeq(delseq);
//				service.uelete(codeGroupDto);
//			}
//		}
//		return "redirect:/Xdm/codeGroupXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/usrIndex")
//	public String usrIndex() {
//		return "/usr/index/index";
//	}
//	
//	@RequestMapping(value = "/Xdm/codeGroupXdmRegister")
//	public String codeGroupXdmRegister(@ModelAttribute("vo") CodeGroupVo vo, Model model, HttpSession httpSession) throws Exception{
//		
//		// login 검사
//		if(httpSession.getAttribute("user") == null) {
//			return "redirect:/Xdm/loginXdm";
//		}
//		model.addAttribute("user", httpSession.getAttribute("user"));
//		
//		if (vo.getIfcgSeq().equals("0") || vo.getIfcgSeq().equals("")) {
////			insert mode
//		} else {
////			update mode
//			model.addAttribute("item", service.selectOne(vo));
//		}
//		return "/xdm/codeGroup/codeGroupXdmRegister";
//	}
	
}