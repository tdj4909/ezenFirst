package com.a2a2lab.module.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a2a2lab.common.config.CustomUserDetails;
import com.a2a2lab.module.code.CodeService;
import com.a2a2lab.module.mail.MailService;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	MailService mailService;
	
	
//	************************************************************
//	관리자
//	************************************************************

	// 멤버 관리 화면
	@RequestMapping("/xdm/member/list")
	public String showMemberManagement(Model model, PageVo pageVo, SearchVo searchVo) {
		// 검색 설정
		model.addAttribute("searchVo", searchVo);
		// 페이징 설정
		pageVo.setParamsPaging(service.countMembersByVo(pageVo, searchVo));
		model.addAttribute("pageVo", pageVo);
		// 통신사
		model.addAttribute("codeList", codeService.findCodesByCodeGroupId("1"));
				
		model.addAttribute("list", service.findMembersByVo(pageVo, searchVo));
		
		return "/xdm/member/memberList";
	}
	
	// 멤버 등록/수정 화면
	@RequestMapping("/xdm/member/edit")
	public String showMemberEdit(Model model, @RequestParam("memberId") String memberId){
		// 통신사
		model.addAttribute("codeList", codeService.findCodesByCodeGroupId("1"));
		// memberId가 있으면 수정, 없으면 등록
		if (!memberId.equals("") && !memberId.equals("0")) {
			model.addAttribute("item", service.findMemberById(memberId));
		}
		return "/xdm/member/memberEdit";
	}
	
	// 멤버 추가
	@RequestMapping("/xdm/member/create")
	public String createMember(MemberDto dto) {
		service.createMember(dto);
		return "redirect:/xdm/member/list";
	}
	
//	// 멤버 수정
//	@RequestMapping("/xdm/member/update")
//	public String updateMember(MemberDto dto) {
//		service.updateMember(dto);
//		return "redirect:/xdm/member/list";
//	}
	
	// 멤버 softDelete
	@RequestMapping("/xdm/member/softDelete")
	public String softDeleteMember(@RequestParam("delSeq") List<String> idList) {
		for(String id : idList) {
			if(!id.equals("")) {
				service.softDeleteMember(id);
			}
		}
		return "redirect:/xdm/member/list";
	}
	
	// 멤버 hardDelete
	@RequestMapping("/xdm/member/hardDelete")
	public String hardDeleteMember(@RequestParam("delSeq") List<String> idList) {
		for(String id : idList) {
			if(!id.equals("")) {
				service.hardDeleteMember(id);
			}
		}
		return "redirect:/xdm/member/list";
	}
	
	
//	************************************************************
//	사용자
//	************************************************************
	// 로그인 화면
	@GetMapping("/tableOrder/sign/loginView")
	public String loginView(Model model) {
		// 임시 로그인 계정
		String tmpEmail = "ServiceAdmin@gmail.com";
		String tmpPwd = "a1234567";
		model.addAttribute("tmpEmail", tmpEmail);
		model.addAttribute("tmpPwd", tmpPwd);
		return "usr/sign/login";
	}
	// 회원가입 화면
	@RequestMapping("/tableOrder/sign/registerView")
	public String registerView(Model model) {
		// 통신사
		model.addAttribute("codeList", codeService.findCodesByCodeGroupId("1"));
		return "usr/sign/register";
	}
	// 회원가입
	@RequestMapping("/tableOrder/sign/register")
	public String registerMember(MemberDto dto) {
		service.createMember(dto);
		return "redirect:/tableOrder/shop/list";
	}
	// 회원가입 email 중복검사
	@ResponseBody
	@RequestMapping("/tableOrder/emailChk")
	public Map<String, Object> emailChk(@RequestParam("email") String email) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
	    if (email != null && !email.isEmpty()) {
	        int result = service.emailChk(email);
	        if (result == 0) {
	            returnMap.put("rt", "success");
	        } else {
	            returnMap.put("rt", "fail");
	        }
	    } else {
	        returnMap.put("rt", "fail");
	        returnMap.put("reason", "email is empty");
	    }
		
		return returnMap;
	}
	// 개인정보 수정 화면
	@RequestMapping("/tableOrder/sign/editView")
	public String editView(Model model, Authentication auth) {
		// 계정 정보
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		model.addAttribute("member", service.findMemberByEmail(userDetails.getUsername()));
		// 통신사
		model.addAttribute("codeList", codeService.findCodesByCodeGroupId("1"));
		return "usr/sign/edit";
	}
	// 개인정보 수정 
	@RequestMapping("/tableOrder/sign/edit")
	public String editMember(MemberDto dto) {
		service.updateMember(dto);
		return "redirect:/tableOrder/shop/list";
	}
	// 비밀번호 변경 화면
	@RequestMapping("/tableOrder/sign/changePwdView")
	public String changePwdView() {
		return "usr/sign/changePwd";
	}
	// 비밀번호 변경 
	@RequestMapping("/tableOrder/sign/changePwd")
	public String changePwd(@RequestParam("currentPassword") String currentPassword,
            				@RequestParam("newPassword") String newPassword,
            				Authentication auth) {
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		String email = userDetails.getUsername();
		service.changePassword(email, currentPassword, newPassword);
		return "redirect:/tableOrder/shop/list";
	}
	
	
//  관리자---------------------------------------------------------------------------	
//	@RequestMapping(value = "/Xdm/memberXdmList")
//	public String memberXdmList(Model model, @ModelAttribute("vo") MemberVo vo, HttpSession httpSession) {
//
//		// login 검사
//		if(httpSession.getAttribute("user") == null) {
//			return "redirect:/Xdm/loginXdm";
//		}
//		httpSession.setMaxInactiveInterval(3600);
//		model.addAttribute("user", httpSession.getAttribute("user"));
//		
//		vo.setParamsPaging(service.selectOneCount(vo));
//		
//		if (vo.getTotalRows() > 0) {
//			model.addAttribute("lists", service.selectList(vo));
//		}
//		
//		return "/xdm/member/memberXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/memberXdmRegister")
//	public String memberXdmRegister(Model model, @ModelAttribute("vo") MemberVo vo, HttpSession httpSession) {
//		
//		// login 검사
//		if(httpSession.getAttribute("user") == null) {
//			return "redirect:/Xdm/loginXdm";
//		}
//		model.addAttribute("user", httpSession.getAttribute("user"));
//		
//		model.addAttribute("mobileCarrierGroup", service.selectMobileCarrierGroup());
//		if (vo.getIfcgSeq().equals("0") || vo.getIfcgSeq().equals("")) {
////			insert mode
//		} else {
////			update mode
//			model.addAttribute("item", service.selectOne(vo));
//		}
//		
//		
//		return "/xdm/member/memberXdmRegister";
//	}
//	
//	@RequestMapping(value = "/Xdm/memberXdmRegisterInst")
//	public String memberXdmRegisterInst(MemberDto memberDto) {
//		service.insert(memberDto);
//		return "redirect:/Xdm/memberXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/memberXdmRegisterUpdt")
//	public String memberXdmRegisterUpdt(MemberDto memberDto) {
//		service.update(memberDto);
//		return "redirect:/Xdm/memberXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/memberXdmUele")
//	public String memberXdmUele(@RequestParam("seq") List<String> seqs) {
//
//		for(String seq : seqs) {
//			if(!seq.isBlank()) {
//				MemberDto dto = new MemberDto();
//				dto.setMemberId(seq);
//				service.uelete(dto);
//			}
//		}
//		return "redirect:/Xdm/memberXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/memberXdmDele")
//	public String memberXdmDele(@RequestParam("seq") List<String> seqs) {
//
//		for(String seq : seqs) {
//			if(!seq.isBlank()) {
//				MemberDto dto = new MemberDto();
//				dto.setMemberId(seq);
//				service.delete(dto);
//			}
//		}
//		return "redirect:/Xdm/memberXdmList";
//	}
//
//	
////	로그인--------------------------------------------------------------
//	
//	@RequestMapping(value = "/Xdm/loginXdm")
//	public String loginXdm(Model model) {
//		
//		// 임시 로그인 계정
//		String tmpEmail = "sample@example.com";
//		String tmpPwd = "12345678";
//		model.addAttribute("tmpEmail", tmpEmail);
//		model.addAttribute("tmpPwd", tmpPwd);
//
//		return "/xdm/sign/loginXdm";
//	}
//	
//	@ResponseBody
//	@RequestMapping(value = "/Xdm/signinXdmProc")
//	public Map<String, Object> signinXdmProc(MemberDto dto, HttpSession httpSession) throws Exception {
//		
//		Map<String, Object> returnMap = new HashMap<String, Object>();
//			
//		// dto의 (email, password)와 DB의 (email, password)가 일치하는지 검사
//		MemberDto result = service.loginChk(dto);
//		
//		// 검사결과 map에 put해서 리턴
//		if(result != null) {
//			httpSession.setAttribute("user", result);
//			returnMap.put("rt", "success");
//		} else {
//			returnMap.put("rt", "fail");
//		}
//
//		return returnMap;
//	}
//	
//	@ResponseBody
//	@RequestMapping(value = "/Xdm/signoutXdmProc")
//	public Map<String, Object> signoutXdmProc(HttpSession httpSession) throws Exception {
//		
//		Map<String, Object> returnMap = new HashMap<String, Object>();
//			
//		httpSession.setAttribute("user", null);
//		returnMap.put("rt", "success");
//		
//
//		return returnMap;
//	}
//	
////	*************************************************************
////	Google SMTP
////	*************************************************************
//	
//////	회원가입 축하 메일
////    public void sendMailWelcome(MemberDto memberDto) throws Exception{
////
////    	
////    	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
////    	MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
////    	mimeMessageHelper.setTo(memberDto.getIfmeEmailFull()); 
////    	mimeMessageHelper.setSubject(templateDto.getIftpTitle());
////    	mimeMessageHelper.setText(contentsHtml, true); 
////    	javaMailSender.send(mimeMessage);
////    	
////    }
//    
////    메일 보내기 test
//    @RequestMapping(value = "/mail")
//    public String sendMail() {
//    	
//    	Thread thread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					mailService.send();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//    	
//    	thread.start();
//    	
//    	return "redirect:/Xdm/memberXdmList";
//	}
	
	
	
}