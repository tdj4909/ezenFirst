package com.a2a2lab.module.account;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a2a2lab.module.member.MemberDto;
import com.a2a2lab.module.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

	@Autowired
	AccountService service;
	@Autowired
	MemberService memberService;

//  사용자 회원가입 or 개인정보 수정-------------------------------------------------------------------
	// 회원가입 or 개인정보 수정 화면
	@RequestMapping(value = "/TableOrder/accountRegister")
	public String accountRegister(Model model, HttpSession httpSession) {

		model.addAttribute("user", httpSession.getAttribute("user"));
		model.addAttribute("mobileCarrierGroup", memberService.selectMobileCarrierGroup());

		return "/usr/account/accountRegister";
	}
	// 회원가입 Insert
	@RequestMapping(value = "/TableOrder/accountRegisterInst")
	public String accountUsrRegisterInst(MemberDto dto) {
		dto.setAdmin(0);
		memberService.insert(dto);
		return "redirect:/TableOrder/shopList";
	}
	// 개인정보 수정
	@RequestMapping(value = "/TableOrder/accountRegisterUpdt")
	public String accountRegisterUpdt(MemberDto dto, HttpSession httpSession) {
		memberService.update(dto);
		MemberDto result = memberService.loginChk(dto);
		httpSession.setAttribute("user", result);
		return "redirect:/TableOrder/shopList";
	}

//  사용자 로그인, 로그아웃-----------------------------------------------------------------
	// 로그인 화면
	@RequestMapping(value = "/TableOrder/accountLogin")
	public String accountLogin(Model model) {
		// 임시 로그인 계정
		String tmpEmail = "sample@example.com";
		String tmpPwd = "12345678";
		model.addAttribute("tmpEmail", tmpEmail);
		model.addAttribute("tmpPwd", tmpPwd);
		return "/usr/account/accountLogin";
	}
	// 로그인
	@ResponseBody
	@RequestMapping(value = "/TableOrder/signinUsrProc")
	public Map<String, Object> signinUsrProc(MemberDto dto, HttpSession httpSession) throws Exception {	
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// dto의 (email, password)와 DB의 (email, password)가 일치하는지 검사
		MemberDto result = memberService.loginChk(dto);
		System.out.println(result);
		
		// 검사결과 map에 put해서 리턴
		if(result != null) {
			httpSession.setAttribute("user", result);
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		return returnMap;
	}
	// 로그아웃
	@ResponseBody
	@RequestMapping(value = "/TableOrder/signoutUsrProc")
	public Map<String, Object> signoutUsrProc(HttpSession httpSession) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
			
		httpSession.setAttribute("user", null);
		returnMap.put("rt", "success");
		
		return returnMap;
	}
	
// 사용자 개인정보 수정--------------------------------------------------------------
	// 개인정보 수정 화면
	@RequestMapping(value = "/TableOrder/accountUpdate")
	public String accountUpdate(Model model, HttpSession httpSession) {
		
		model.addAttribute("user", httpSession.getAttribute("user"));
		
		return "/usr/account/accountUpdate";
	}
	// 개인정보 수정
	@RequestMapping(value = "/TableOrder/accountUpdateUpdt")
	public String accountUpdateUpdt(MemberDto memberDto) {
		
		return "redirect:/TableOrder/shopList";
	}
	
}