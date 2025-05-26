package com.a2a2lab.module.cart;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CartDao {
	
	public List<CartDto> findCartsByMemberId(String memberId);	
	public CartDto findCartById(String cartId);
	public CartDto findCartByMemberIdAndProductId(CartDto dto);	
	
	// Select를 제외한 CRUD
	public int saveCart(CartDto dto);
	public int updateCart(CartDto dto);
	public int softDeleteCart(String cartId);
	public int softDeleteCartByMemberId(String memberId);
	public int softDeleteCartByProductId(String productId);
	
}
