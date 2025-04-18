package com.a2a2lab.module.mail;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	public void send() throws Exception {
		
		MailDto mailDto = new MailDto();
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
    	
	}
	
}
