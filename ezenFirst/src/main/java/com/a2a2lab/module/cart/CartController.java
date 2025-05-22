package com.a2a2lab.module.cart;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a2a2lab.common.config.CustomUserDetails;

@Controller
public class CartController{
	
	@Autowired
	CartService service;
	
	// 장바구니 Ajax
	@GetMapping("/tableOrder/shop/cart")
	public String getCartFragment(Model model, Authentication auth) {
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		model.addAttribute("list", service.findCartsByMemberId(userDetails.getMemberId()));
		return "usr/fragment/cart :: cartFragment";
	}
	// 장바구니 추가
	@PostMapping("/tableOrder/cart/add")
	@ResponseBody
	public Map<String, String> addCart(@RequestBody CartDto dto, Authentication auth) {
		Map<String, String> result = new HashMap<>();
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		dto.setMemberId(userDetails.getMemberId());
		// 장바구니 중복검사
		CartDto resultDto = service.findCartByMemberIdAndProductId(dto);
		if(resultDto == null) { // 장바구니 상품 새로 추가
			service.saveCart(dto);
		} else { // 장바구니에서 동일상품 개수 +1
			resultDto.setQuantity(resultDto.getQuantity()+1);
			service.updateCart(resultDto);
		}
		result.put("status", "ok");
		return result;
	}
	// 장바구니 수정
	@PostMapping("/tableOrder/cart/update")
	@ResponseBody
	public Map<String, String> updateCart(@RequestParam("qty") int qty, @RequestParam("cartId") String cartId) {
		Map<String, String> result = new HashMap<>();
		CartDto dto = service.findCartById(cartId);
		if(qty == 1) {
			dto.setQuantity(dto.getQuantity()+1);
			service.updateCart(dto);
		} else if (qty == -1 && dto.getQuantity() > 1) {
			dto.setQuantity(dto.getQuantity()-1);
			service.updateCart(dto);
		} else if(qty == 0){
			dto.setIsDeleted(1);
			service.updateCart(dto);
		}
		
		result.put("status", "ok");
		return result;
	}
	
}