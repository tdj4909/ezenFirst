package com.a2a2lab.module.account;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import com.a2a2lab.module.member.MemberDto;
import com.a2a2lab.module.member.MemberService;

@Controller
public class AccountController {

	@Autowired
	AccountService service;
	@Autowired
	MemberService memberService;
	
	// 사용자 회원가입 화면
	@RequestMapping(value = "/TableOrder/accountRegister")
	public String accountUsrRegister(Model model) {
		model.addAttribute("mobileCarrierGroup", memberService.selectMobileCarrierGroup());
		return "/usr/account/accountRegister";
	}
	
	// 사용자 회원가입 Insert
	@RequestMapping(value = "/TableOrder/accountRegisterInst")
	public String accountUsrRegisterInst(MemberDto dto) {
		dto.setAdmin(0);
		memberService.insert(dto);
		return "redirect:/TableOrder/accountRegister";
	}
	
}