package com.a2a2lab.module.cart;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CartDao {
	
	public List<CartDto> findCartsByMemberId(String memberId);	
	public CartDto findCartById(String cartId);
	public CartDto findCartByMemberIdAndProductId(CartDto dto);	
	public int saveCart(CartDto dto);
	public int updateCart(CartDto dto);
	public int softDeleteCart(String cartId);
	
}
