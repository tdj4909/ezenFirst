package com.a2a2lab.module.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.code.CodeVo;
import com.a2a2lab.module.codeGroup.CodeGroupDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@RequestMapping(value = "/accountUsrRegister")
	public String accountUsrRegister(Model model) {
		model.addAttribute("mobileCarrierGroup", service.selectMobileCarrierGroup());
		return "/usr/account/accountUsrRegister";
	}
	
	@RequestMapping(value = "/accountUsrRegisterInst")
	public String accountUsrRegisterInst(MemberDto memberDto) {
		System.out.println(memberDto.getMobileCarrier());
		memberDto.setAdmin(0);
		service.insert(memberDto);
		return "redirect:/accountUsrRegister";
	}
	
	@RequestMapping(value = "/memberXdmList")
	public String memberXdmList(Model model, @ModelAttribute("vo") MemberVo vo) {

		vo.setParamsPaging(service.selectOneCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("lists", service.selectList(vo));
		}
		
		return "/xdm/member/memberXdmList";
	}
	
	@RequestMapping(value = "/memberXdmRegister")
	public String memberXdmRegister(Model model, @ModelAttribute("vo") MemberVo vo) {
		
		model.addAttribute("mobileCarrierGroup", service.selectMobileCarrierGroup());
		if (vo.getIfcgSeq().equals("0") || vo.getIfcgSeq().equals("")) {
//			insert mode
		} else {
//			update mode
			model.addAttribute("item", service.selectOne(vo));
		}
		
		
		return "/xdm/member/memberXdmRegister";
	}
	
	@RequestMapping(value = "/memberXdmRegisterInst")
	public String memberXdmRegisterInst(MemberDto memberDto) {

		service.insert(memberDto);
		return "redirect:/memberXdmList";
	}
	
	
	
//	---------로그인---------------------------------------
	
	@RequestMapping(value = "/login")
	public String login() {

		return "/xdm/sign/login";
	}
	
	@ResponseBody
	@RequestMapping(value = "/signinXdmProc")
	public Map<String, Object> signinXdmProc(MemberDto dto, HttpSession httpSession) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
			
		// dto의 (email, password)와 DB의 (email, password)가 일치하는지 검사
		int result = service.loginChk(dto);
		
		// 검사결과 map에 put해서 리턴
		if(result != 0) {
			httpSession.setAttribute("user", dto.getEmail());
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}

		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/signoutXdmProc")
	public Map<String, Object> signoutXdmProc(HttpSession httpSession) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
			
		httpSession.setAttribute("user", null);
		returnMap.put("rt", "success");
		

		return returnMap;
	}
	
	
}