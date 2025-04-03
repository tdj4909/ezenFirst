package com.a2a2lab.module.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.a2a2lab.module.member.MemberService;
import com.a2a2lab.module.orders.OrdersDto;
import com.a2a2lab.module.orders.OrdersService;
import com.a2a2lab.module.review.ReviewService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController{
	
	@Autowired
	OrdersService ordersService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/Xdm/index")
	public String index(Model model, HttpSession httpSession) {
		
		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/Xdm/loginXdm";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
		model.addAttribute("ordersCount", ordersService.ordersCount());
		model.addAttribute("ordersSum", ordersService.ordersSum());
		model.addAttribute("reviewCount", reviewService.reviewCount());
		model.addAttribute("memberCount", memberService.memberCount());
				
		return "/xdm/index/index";
	}
	
	
}