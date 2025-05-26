package com.a2a2lab.module.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.a2a2lab.module.member.MemberService;
import com.a2a2lab.module.order.OrderService;
import com.a2a2lab.module.review.ReviewService;

@Controller
public class IndexController{
	
	@Autowired
	OrderService orderService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/xdm/index")
	public String index(Model model) {
		
		model.addAttribute("ordersCount", orderService.countAllOrder());
		model.addAttribute("ordersSum", orderService.sumAllOrder());
		model.addAttribute("reviewCount", reviewService.countAllReview());
		model.addAttribute("memberCount", memberService.countAllMember());
		
		return "/xdm/index/index";
	}
	
}