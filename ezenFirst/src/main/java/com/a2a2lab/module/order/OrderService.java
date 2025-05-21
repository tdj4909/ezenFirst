package com.a2a2lab.module.order;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Service
public class OrderService {

	@Autowired
	OrderDao dao;
	
	public int countOrderMastersByVo(PageVo pageVo, SearchVo searchVo) {
		return dao.countOrderMastersByVo(pageVo, searchVo);
	}
	
	public List<OrderDto> findOrderMastersByVo(PageVo pageVo, SearchVo searchVo) {
		return dao.findOrderMastersByVo(pageVo, searchVo);
	}
	
	public OrderDto findOrderMasterById(String id) {
		return dao.findOrderMasterById(id);
	}
	
	public List<OrderDto> findOrderDetailsByOrderMasterId(String id) {
		return dao.findOrderDetailsByOrderMasterId(id);
	}
	
	public List<OrderDto> findCartsByMemberId(String memberId) {
		return dao.findCartsByMemberId(memberId);
	}
	
	public OrderDto findCartByMemberIdAndProductId(OrderDto dto) {
		return dao.findCartByMemberIdAndProductId(dto);
	}
	
	public Integer countAllOrder() {
		return dao.countAllOrder();
	}
	
	public Integer sumAllOrder() {
		return dao.sumAllOrder();
	}
	
	public int saveCart(OrderDto dto) {
		return dao.saveCart(dto);
	}
	
	public int updateCart(OrderDto dto) {
		return dao.updateCart(dto);
	}
	
	public int updateOrderMaster(OrderDto dto) {
		return dao.updateOrderMaster(dto);
	}
	
	public int softDeleteOrderMaster(String id) {
		return dao.softDeleteOrderMaster(id);
	}
	
	public int hardDeleteOrderMaster(String id) {
		return dao.hardDeleteOrderMaster(id);
	}
	
	


}
