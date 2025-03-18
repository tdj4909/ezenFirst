package com.a2a2lab.module.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.codeGroup.CodeGroupDto;

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
		service.insert(memberDto);
		return "redirect:/accountUsrRegister";
	}
	
	@RequestMapping(value = "/memberXdmList")
	public String memberXdmList(Model model, MemberVo vo) {

		vo.setParamsPaging(service.selectOneCount());
		model.addAttribute("list", service.selectList(vo));
		model.addAttribute("vo", vo);
		
		return "/xdm/member/memberXdmList";
	}
	
}