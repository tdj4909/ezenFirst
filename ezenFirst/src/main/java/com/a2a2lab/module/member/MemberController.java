package com.a2a2lab.module.member;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a2a2lab.module.mail.MailDto;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@Autowired
	JavaMailSender javaMailSender;
	
//  관리자---------------------------------------------------------------------------	
	@RequestMapping(value = "/Xdm/memberXdmList")
	public String memberXdmList(Model model, @ModelAttribute("vo") MemberVo vo, HttpSession httpSession) {

		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/Xdm/loginXdm";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
		
		vo.setParamsPaging(service.selectOneCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("lists", service.selectList(vo));
		}
		
		return "/xdm/member/memberXdmList";
	}
	
	@RequestMapping(value = "/Xdm/memberXdmRegister")
	public String memberXdmRegister(Model model, @ModelAttribute("vo") MemberVo vo, HttpSession httpSession) {
		
		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/Xdm/loginXdm";
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
	
	@RequestMapping(value = "/Xdm/memberXdmRegisterInst")
	public String memberXdmRegisterInst(MemberDto memberDto) {
		service.insert(memberDto);
		return "redirect:/Xdm/memberXdmList";
	}
	
	@RequestMapping(value = "/Xdm/memberXdmRegisterUpdt")
	public String memberXdmRegisterUpdt(MemberDto memberDto) {
		service.update(memberDto);
		return "redirect:/Xdm/memberXdmList";
	}
	
	@RequestMapping(value = "/Xdm/memberXdmUele")
	public String memberXdmUele(@RequestParam("seq") List<String> seqs) {

		for(String seq : seqs) {
			if(!seq.isBlank()) {
				MemberDto dto = new MemberDto();
				dto.setSeq(seq);
				service.uelete(dto);
			}
		}
		return "redirect:/Xdm/memberXdmList";
	}
	
	@RequestMapping(value = "/Xdm/memberXdmDele")
	public String memberXdmDele(@RequestParam("seq") List<String> seqs) {

		for(String seq : seqs) {
			if(!seq.isBlank()) {
				MemberDto dto = new MemberDto();
				dto.setSeq(seq);
				service.delete(dto);
			}
		}
		return "redirect:/Xdm/memberXdmList";
	}

	
//	로그인--------------------------------------------------------------
	
	@RequestMapping(value = "/Xdm/loginXdm")
	public String loginXdm(Model model) {
		
		// 임시 로그인 계정
		String tmpEmail = "sample@example.com";
		String tmpPwd = "12345678";
		model.addAttribute("tmpEmail", tmpEmail);
		model.addAttribute("tmpPwd", tmpPwd);

		return "/xdm/sign/loginXdm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/Xdm/signinXdmProc")
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
	@RequestMapping(value = "/Xdm/signoutXdmProc")
	public Map<String, Object> signoutXdmProc(HttpSession httpSession) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
			
		httpSession.setAttribute("user", null);
		returnMap.put("rt", "success");
		

		return returnMap;
	}
	
//	*************************************************************
//	Google SMTP
//	*************************************************************
	
////	회원가입 축하 메일
//    public void sendMailWelcome(MemberDto memberDto) throws Exception{
//
//    	
//    	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//    	MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
//    	mimeMessageHelper.setTo(memberDto.getIfmeEmailFull()); 
//    	mimeMessageHelper.setSubject(templateDto.getIftpTitle());
//    	mimeMessageHelper.setText(contentsHtml, true); 
//    	javaMailSender.send(mimeMessage);
//    	
//    }
    
//    메일 보내기 test
    @RequestMapping(value = "/mail")
    public String sendMail(MailDto mailDto) throws Exception {
    	String addr = "";
    	mailDto.setAddress(addr);
    	mailDto.setTitle("SMTP 테스트 " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")));
    	mailDto.setMessage("테스트 내용\n" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")));
		
    	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    	MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    	mimeMessageHelper.setTo(mailDto.getAddress());
    	mimeMessageHelper.setSubject(mailDto.getTitle());
    	mimeMessageHelper.setText(mailDto.getMessage());
    	mimeMessageHelper.addInline("sampleImg", new File("src/main/resources/static/xdm/template/AdminUI/assets/img/card.jpg"));
    	javaMailSender.send(mimeMessage);
    	
    	return "redirect:/Xdm/memberXdmList";
	}
	
	
	
}