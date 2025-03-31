package com.a2a2lab.module.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.code.CodeVo;
import com.a2a2lab.module.codeGroup.CodeGroupDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
//  관리자---------------------------------------------------------------------------	
	@RequestMapping(value = "/memberXdmList")
	public String memberXdmList(Model model, @ModelAttribute("vo") MemberVo vo, HttpSession httpSession) {

		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/loginXdm";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
		
		vo.setParamsPaging(service.selectOneCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("lists", service.selectList(vo));
		}
		
		return "/xdm/member/memberXdmList";
	}
	
	@RequestMapping(value = "/memberXdmRegister")
	public String memberXdmRegister(Model model, @ModelAttribute("vo") MemberVo vo, HttpSession httpSession) {
		
		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/loginXdm";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
		
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
	
	@RequestMapping(value = "/memberXdmRegisterUpdt")
	public String memberXdmRegisterUpdt(MemberDto memberDto) {
		service.update(memberDto);
		return "redirect:/memberXdmList";
	}
	
	@RequestMapping(value = "/memberXdmUele")
	public String memberXdmUele(@RequestParam("seq") List<String> seqs) {

		for(String seq : seqs) {
			if(!seq.isBlank()) {
				MemberDto dto = new MemberDto();
				dto.setSeq(seq);
				service.uelete(dto);
			}
		}
		return "redirect:/memberXdmList";
	}

	
//	로그인--------------------------------------------------------------
	
	@RequestMapping(value = "/loginXdm")
	public String loginXdm(Model model) {
		
		// 임시 로그인 계정
		String tmpEmail = "sample@example.com";
		String tmpPwd = "12345678";
		model.addAttribute("tmpEmail", tmpEmail);
		model.addAttribute("tmpPwd", tmpPwd);

		return "/xdm/sign/loginXdm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/signinXdmProc")
	public Map<String, Object> signinXdmProc(MemberDto dto, HttpSession httpSession) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
			
		// dto의 (email, password)와 DB의 (email, password)가 일치하는지 검사
		MemberDto result = service.loginChk(dto);
		
		// 검사결과 map에 put해서 리턴
		if(result != null) {
			httpSession.setAttribute("user", result);
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