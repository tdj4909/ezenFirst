package com.a2a2lab.module.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a2a2lab.common.config.CustomUserDetails;
import com.a2a2lab.module.cart.CartService;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Controller
public class OrderController{
	
	@Autowired
	OrderService service;
	
	@Autowired
	CartService cartService;
	
//	************************************************************
//	관리자
//	************************************************************
	// 주문 관리 화면
	@RequestMapping("/xdm/service/order/list")
	public String showOrderManagement(Model model, PageVo pageVo, SearchVo searchVo) {
		// 검색 설정
		model.addAttribute("searchVo", searchVo);
		// 페이징 설정
		pageVo.setParamsPaging(service.countOrderMastersByVo(pageVo, searchVo));
		model.addAttribute("pageVo", pageVo);
		// 주문 출력
		model.addAttribute("list", service.findOrderMastersByVo(pageVo, searchVo));
		return "xdm/order/orderList";
	}
	// 주문 상세
	@RequestMapping("/xdm/service/order/detail")
	public String ordersXdmOne(Model model, @RequestParam("orderMasterId") String orderMasterId) {
		model.addAttribute("orderMaster", service.findOrderMasterById(orderMasterId));
		model.addAttribute("list", service.findOrderDetailsByOrderMasterId(orderMasterId));
		return "xdm/order/orderDetail";
	}
	// 주문 Update
	@RequestMapping("/xdm/service/order/update")
	@ResponseBody
	public String updateOrderMaster(@RequestParam("id") List<String> idList, @RequestParam("status") List<Integer> statuses) {
		for(int i = 0; i < idList.size(); i++) {
			OrderDto dto = new OrderDto();
			dto.setOrderMasterId(idList.get(i));
			dto.setStatus(statuses.get(i));
			service.updateOrderMaster(dto);
		}
		return "redirect:/xdm/service/order/list";
	}
	// 주문 softDelete
	@RequestMapping("/xdm/service/order/softDelete")
	public String softDeleteOrderMaster(@RequestParam("delSeq") List<String> idList) {
		for(String id : idList) {
			if(!id.equals("")) {
				service.softDeleteOrderMaster(id);
			}
		}
		return "redirect:/xdm/service/order/list";
	}
	// 주문 hardDelete
	@RequestMapping("/xdm/service/order/hardDelete")
	public String hardDeleteOrderMaster(@RequestParam("delSeq") List<String> idList) {
		for(String id : idList) {
			if(!id.equals("")) {
				service.hardDeleteOrderMaster(id);
			}
		}
		return "redirect:/xdm/service/order/list";
	}

//	************************************************************
//	사용자
//	************************************************************
	// 결제 화면
	@PostMapping("/tableOrder/order/checkout")
	public String ordersCheckout(Model model, Authentication auth) {
		return "usr/order/orderCheckout";
	}
	// 결제 화면 Ajax
	@GetMapping("/tableOrder/order/checkout/orderDetails")
	public String getorderDetailsFragment(Model model, Authentication auth) {
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		model.addAttribute("list", cartService.findCartsByMemberId(userDetails.getMemberId()));
		return "usr/fragment/orderDetails :: orderDetailsFragment";
	}
	// 결제
//	@RequestMapping(value = "/TableOrder/ordersCompleted")
//	public String ordersCompleted(@RequestParam("menu_seq") List<String> menu_seqList,
//								  @RequestParam("quantity") List<Integer> quantityList,
//								  @RequestParam("odTotalPrice") Integer odTotalPrice,
//								  HttpSession httpSession) {
//		// 주문 Insert
//		MemberDto memberDto = (MemberDto) httpSession.getAttribute("user");
//		OrdersDto dto = new OrdersDto();
//		dto.setOdTotalPrice(odTotalPrice);
////		dto.setUser_seq(memberDto.getSeq());
//		service.insertOrder(dto);
//		
//		// 개별 주문 Insert
//		for(int i = 0; i < menu_seqList.size(); i++) {
//			dto.setMenu_seq(menu_seqList.get(i));
//			dto.setQuantity(quantityList.get(i));
//			service.insertOrderMenu(dto);
//		}
//		
//		return "redirect:/TableOrder/ordersDetail/"+ dto.getOrders_seq();
//	}
//	
//	// 주문상세 화면
//	@RequestMapping(value = "/TableOrder/ordersDetail/{seq}")
//	public String ordersDetail(@PathVariable("seq") String seq, Model model) {
//	
//		OrdersDto dto = new OrdersDto();
//		dto.setSeq(seq);
//		// 주문 selectOne
//		model.addAttribute("order", service.selectOne(dto));
//		// 상세주문 selectAll by oreders_seq
//		model.addAttribute("orderMenu", service.selectOneList(dto));
//		
//		return "/usr/orders/ordersDetail";
//	}
//	
//	
//	// 주문내역 화면
//	@RequestMapping(value = "/TableOrder/ordersHistory")
//	public String ordersHistory(@RequestParam(name = "page", defaultValue="1") int page, Model model, HttpSession httpSession) {
//	
//		// 로그인한 member의 id 추출
//		MemberDto memberDto = (MemberDto) httpSession.getAttribute("user");
////		String user_seq = memberDto.getSeq();
//		// 페이징 설정
//		PageVo pageVo = new PageVo();
//		pageVo.setRowNumToShow(5);
//		pageVo.setThisPage(page);
////		pageVo.setParamsPaging(service.countOrdersByMemberSeq(user_seq));
//		model.addAttribute("pageVo", pageVo);
//		// 주문 목록 출력
////		model.addAttribute("list", service.findOrdersByMemberSeq(user_seq, pageVo));
//		
//		return "/usr/orders/ordersHistory";
//	}
//	// 주문내역 삭제
//	@RequestMapping("/TableOrder/ordersUsrUele")
//	public ResponseEntity<String> ordersUsrUele(@RequestBody Map<String, String> map) {
//		
//		OrdersDto dto = new OrdersDto();
//		dto.setSeq(map.get("seq"));
//		service.uelete(dto);
//		
//		return ResponseEntity.ok("삭제 성공");
//	}
	
}