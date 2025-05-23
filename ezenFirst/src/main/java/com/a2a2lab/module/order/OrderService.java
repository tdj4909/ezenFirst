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
	
	public int countOrderMastersByVo(SearchVo searchVo) {
		return dao.countOrderMastersByVo(searchVo);
	}
	
	public List<OrderDto> findOrderMastersByVo(PageVo pageVo, SearchVo searchVo) {
		return dao.findOrderMastersByVo(pageVo, searchVo);
	}
	
	public int countOrderMastersByMemberId(String memberId) {
		return dao.countOrderMastersByMemberId(memberId);
	}
	
	public List<OrderDto> findOrderMastersByMemberId(PageVo pageVo, String memberId) {
		return dao.findOrderMastersByMemberId(pageVo, memberId);
	}
	
	public OrderDto findOrderMasterById(String id) {
		return dao.findOrderMasterById(id);
	}
	
	public List<OrderDto> findOrderDetailsByOrderMasterId(String id) {
		return dao.findOrderDetailsByOrderMasterId(id);
	}
	
	public Integer countAllOrder() {
		return dao.countAllOrder();
	}
	
	public Integer sumAllOrder() {
		return dao.sumAllOrder();
	}
	
	public int saveOrderMaster(OrderDto dto) {
		return dao.saveOrderMaster(dto);
	}
	
	public int saveOrderDetail(OrderDto dto) {
		return dao.saveOrderDetail(dto);
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
