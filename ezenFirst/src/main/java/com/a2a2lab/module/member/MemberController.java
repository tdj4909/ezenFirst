package com.a2a2lab.module.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import jakarta.servlet.http.HttpServletResponse;

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
		model.addAttribute("codeList", codeService.getCodesByCodegroupId("1"));
		// 멤버 출력		
		model.addAttribute("list", service.findMembersByVo(pageVo, searchVo));
		return "xdm/member/memberList";
	}
	// 멤버 등록/수정 화면
	@RequestMapping("/xdm/member/edit")
	public String showMemberEdit(Model model, @RequestParam("memberId") String memberId){
		// 통신사
		model.addAttribute("codeList", codeService.getCodesByCodegroupId("1"));
		// memberId가 있으면 수정, 없으면 등록
		if (!memberId.equals("") && !memberId.equals("0")) {
			model.addAttribute("item", service.findMemberById(memberId));
		}
		return "xdm/member/memberEdit";
	}
	// 멤버 추가
	@RequestMapping("/xdm/member/create")
	public String createMember(MemberDto dto) {
		service.createMember(dto);
		return "redirect:/xdm/member/list";
	}
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
	// Excel 다운로드
	@GetMapping("/xdm/member/excel")
	public void downloadMemberExcel(HttpServletResponse response, PageVo pageVo, SearchVo searchVo) throws IOException {
	    List<MemberDto> members = service.findMembersByVo(pageVo, searchVo); // 필터링 적용된 목록

	    // 엑셀 워크북 생성
	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("Members");

	    // 헤더
	    Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue("이메일");
	    header.createCell(1).setCellValue("이름");
	    header.createCell(2).setCellValue("성별");
	    header.createCell(3).setCellValue("생년월일");
	    header.createCell(4).setCellValue("통신사");
	    header.createCell(5).setCellValue("전화번호");
	    header.createCell(6).setCellValue("등록일");
	    header.createCell(7).setCellValue("수정일");

	    // 내용
	    int rowNum = 1;
	    for (MemberDto member : members) {
	        Row row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue(member.getEmail());
	        row.createCell(1).setCellValue(member.getName());
	        row.createCell(2).setCellValue(member.getGender());
	        row.createCell(3).setCellValue(member.getBirthday());
	        row.createCell(4).setCellValue(member.getMobileCarrierName());
	        row.createCell(5).setCellValue(member.getPhone());
	        row.createCell(6).setCellValue(member.getCreatedAt());
	        row.createCell(7).setCellValue(member.getUpdatedAt() == null ? "" : member.getUpdatedAt());
	    }
	    
	    // 열 너비 자동 조절
	    for (int i = 0; i < 8; i++) {
	        sheet.autoSizeColumn(i);
	        sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1024); // 약간 여유 (한글 대응)
	    }

	    // 응답 설정
	    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	    response.setHeader("Content-Disposition", "attachment; filename=members.xlsx");

	    // 엑셀 파일 내보내기
	    workbook.write(response.getOutputStream());
	    workbook.close();
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
		model.addAttribute("codeList", codeService.getCodesByCodegroupId("1"));
		return "usr/sign/register";
	}
	// 회원가입
	@RequestMapping("/tableOrder/sign/register")
	public String registerMember(MemberDto dto) {
		service.createMember(dto);
		return "redirect:/tableOrder/shop/list";
	}
	// 회원가입 email 중복검사
	@RequestMapping("/tableOrder/emailChk")
	@ResponseBody
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
		model.addAttribute("codeList", codeService.getCodesByCodegroupId("1"));
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