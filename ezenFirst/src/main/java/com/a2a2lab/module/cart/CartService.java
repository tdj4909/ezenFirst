package com.a2a2lab.module.cart;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

	@Autowired
	CartDao dao;
	
	public List<CartDto> findCartsByMemberId(String memberId) {
		return dao.findCartsByMemberId(memberId);
	}
	
	public CartDto findCartById(String cartId) {
		return dao.findCartById(cartId);
	}
	
	public CartDto findCartByMemberIdAndProductId(CartDto dto) {
		return dao.findCartByMemberIdAndProductId(dto);
	}
	
	public int saveCart(CartDto dto) {
		return dao.saveCart(dto);
	}
	
	public int updateCart(CartDto dto) {
		return dao.updateCart(dto);
	}
	
	public int softDeleteCart(String cartId) {
		return dao.softDeleteCart(cartId);
	}

}
