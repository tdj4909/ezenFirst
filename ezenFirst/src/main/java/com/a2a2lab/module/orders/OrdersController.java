package com.a2a2lab.module.orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrdersController{
	
	@Autowired
	OrdersService service;
	
    // 관리자
	// 주문관리 화면
	@RequestMapping(value = "/Xdm/ordersXdmList")
	public String ordersXdmList(@ModelAttribute("vo") OrdersVo vo, Model model, HttpSession httpSession) {
		
		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/Xdm/loginXdm";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
				
		vo.setParamsPaging(service.selectOneCount(vo));
	
		if (vo.getTotalRows() > 0) {
			model.addAttribute("lists", service.selectList(vo));
		}
	
		return "/xdm/orders/ordersXdmList";
	}
	// 주문관리 Uelete
	@RequestMapping(value = "/Xdm/ordersXdmUele")
	public String ordersXdmUele(@RequestParam("seq") List<String> seqs) {
		
		for(String seq : seqs) {
			if(!seq.isBlank()) {
				OrdersDto dto = new OrdersDto();
				dto.setSeq(seq);
				service.uelete(dto);
			}
		}
		return "redirect:/Xdm/ordersXdmList";
	}
	// 주문관리 Delete
	@RequestMapping(value = "/Xdm/ordersXdmDele")
	public String ordersXdmDele(@RequestParam("seq") List<String> seqs) {
		
		for(String seq : seqs) {
			if(!seq.isBlank()) {
				OrdersDto dto = new OrdersDto();
				dto.setSeq(seq);
				service.delete(dto);
			}
		}
		return "redirect:/Xdm/ordersXdmList";
	}
	// 주문관리 Update
	@RequestMapping(value = "/Xdm/ordersXdmUdpt")
	@ResponseBody
	public String ordersXdmUdpt(@RequestParam("seq") List<String> seqs, @RequestParam("ordersStatus") List<Integer> ordersStatuses) {
		
		for(int i = 0; i < seqs.size(); i++) {
			OrdersDto dto = new OrdersDto();
			dto.setSeq(seqs.get(i));
			dto.setOrdersStatus(ordersStatuses.get(i));
			service.update(dto);
		}

		return "redirect:/Xdm/ordersXdmList";
	}
	// 주문관리 상세
	@RequestMapping(value = "/Xdm/ordersXdmOne")
	public String ordersXdmOne(Model model, HttpSession httpSession, @RequestParam("choice") String seq) {
		
		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/Xdm/loginXdm";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));

		OrdersDto dto = new OrdersDto();
		dto.setSeq(seq);
		model.addAttribute("list", service.selectOne(dto));
		model.addAttribute("lists", service.selectOneList(dto));
		
	
		return "/xdm/orders/ordersXdmOne";
	}
	
//	*************************************************************************
	
    // 사용자
	// 결제 화면
	@RequestMapping(value = "/TableOrder/ordersCheckout")
	public String ordersCheckout() {
	
		return "/usr/orders/ordersCheckout";
	}
	// 주문 상세 화면
	@RequestMapping(value = "/TableOrder/ordersDetail")
	public String ordersDetail() {
	
		return "/usr/orders/ordersDetail";
	}
	// 주문 목록
	@RequestMapping(value = "/TableOrder/ordersHistory")
	public String ordersHistory() {
	
		return "/usr/orders/ordersHistory";
	}
	
}