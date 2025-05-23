package com.a2a2lab.module.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a2a2lab.common.config.CustomUserDetails;
import com.a2a2lab.module.cart.CartService;
import com.a2a2lab.module.product.ProductService;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Controller
public class OrderController{

	@Autowired
	OrderService service;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	ProductService productService;
	
//	************************************************************
//	관리자
//	************************************************************
	// 주문 관리 화면
	@RequestMapping("/xdm/service/order/list")
	public String showOrderManagement(Model model, PageVo pageVo, SearchVo searchVo) {
		// 검색 설정
		model.addAttribute("searchVo", searchVo);
		// 페이징 설정
		pageVo.setParamsPaging(service.countOrderMastersByVo(searchVo));
		model.addAttribute("pageVo", pageVo);
		// 주문 출력
		model.addAttribute("list", service.findOrderMastersByVo(pageVo, searchVo));
		return "xdm/order/orderList";
	}
	// 주문 상세
	@RequestMapping("/xdm/service/order/detail")
	public String ordersXdmOne(Model model, @RequestParam("orderMasterId") String orderMasterId) {
		// orderMaster 주문 하나 
		model.addAttribute("orderMaster", service.findOrderMasterById(orderMasterId));
		// orderMaster에 속한 orderDetail 주문들
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
	public String orderCheckout() {
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
	@PostMapping("/tableOrder/order/completed")
	public String orderCompleted(@RequestParam("productId") List<String> productIdList,
							      @RequestParam("cartId") List<String> cartIdList,
								  @RequestParam("quantity") List<Integer> quantityList,
								  @RequestParam("orderDetailPrice") List<Integer> orderDetailPriceList,
								  @RequestParam("odTotalPrice") Integer odTotalPrice,
								  Authentication auth) {
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		OrderDto dto = new OrderDto();
		dto.setMemberId(userDetails.getMemberId());
		dto.setPrice(odTotalPrice);
		service.saveOrderMaster(dto);
		
		// 개별 주문 Insert
		for(int i = 0; i < productIdList.size(); i++) {
			dto.setProductId(productIdList.get(i));
			dto.setQuantity(quantityList.get(i));
			dto.setOrderDetailPrice(orderDetailPriceList.get(i));
			service.saveOrderDetail(dto);
			cartService.softDeleteCart(cartIdList.get(i));
			
			// 메뉴 orderCount +1
			productService.updateOrderCountByProductId(productIdList.get(i));
		}
		
		return "redirect:/tableOrder/order/history/detail/"+ dto.getOrderMasterId();
	}
	// 주문상세 화면
	@RequestMapping("/tableOrder/order/history/detail/{orderMasterId}")
	public String orderDetail(@PathVariable("orderMasterId") String orderMasterId, Model model) {
		// orderMaster 주문 하나 
		model.addAttribute("orderMaster", service.findOrderMasterById(orderMasterId));
		// orderMaster에 속한 orderDetail 주문들
		model.addAttribute("orderDetail", service.findOrderDetailsByOrderMasterId(orderMasterId));
		return "usr/order/orderDetail";
	}
	// 주문내역 화면
	@RequestMapping("/tableOrder/order/history")
	public String orderHistory(@RequestParam(name = "page", defaultValue="1") int page,
								Model model,
								Authentication auth) {
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		String memberId = userDetails.getMemberId();
		// 페이징 설정
		PageVo pageVo = new PageVo();
		pageVo.setThisPage(page);
		pageVo.setParamsPaging(service.countOrderMastersByMemberId(memberId));
		model.addAttribute("pageVo", pageVo);
		// 주문 목록 출력
		model.addAttribute("list", service.findOrderMastersByMemberId(pageVo, memberId));
		return "/usr/order/orderHistory";
	}
	// 주문내역 삭제
	@RequestMapping("/tableOrder/order/history/softDelete")
	public ResponseEntity<String> softDeleteOrderHistory(@RequestBody Map<String, String> map) {
		service.softDeleteOrderMaster(map.get("orderMasterId"));
		return ResponseEntity.ok("삭제 성공");
	}
	
}