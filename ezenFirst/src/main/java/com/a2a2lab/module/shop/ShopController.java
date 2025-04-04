package com.a2a2lab.module.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import com.a2a2lab.module.member.MemberService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ShopController {

//	@Autowired
//	ShopService service;
	@Autowired
	MemberService memberService;
	
	// 상품 리스트 화면
	@RequestMapping(value = "/TableOrder/shopList")
	public String shopList(Model model, HttpSession httpSession) {
		
		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/TableOrder/accountLogin";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
		
		return "/usr/shop/shopList";
	}
	
}