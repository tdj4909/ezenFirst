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
	
	public Integer countAllOrder() {
		return dao.countAllOrder();
	}
	
	public Integer sumAllOrder() {
		return dao.sumAllOrder();
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
	
	
	
//	public int insertOrder(OrderDto dto) {
//		return dao.insertOrder(dto);
//	}
//	
//	public int insertOrderMenu(OrderDto dto) {
//		return dao.insertOrderMenu(dto);
//	}
//	
//	public int uelete(OrderDto dto) {
//		return dao.uelete(dto);
//	}
//	
//	public int delete(OrderDto dto) {
//		return dao.delete(dto);
//	}
//	
//	public int update(OrderDto dto) {
//		return dao.update(dto);
//	}
//
//	public int selectOneCount(OrderVo vo) {
//		return dao.selectOneCount(vo);
//	}
//	
//	public int ordersCount() {
//		return dao.ordersCount();
//	}
//	
//	public int ordersSum() {
//		return dao.ordersSum();
//	}
//	
//	public OrderDto selectOne(OrderDto dto) {
//		return dao.selectOne(dto);
//	}
//	
//	public List<OrderDto> selectOneList(OrderDto dto) {
//		return dao.selectOneList(dto);
//	}
//	
//	public List<OrderDto> selectList(OrderVo vo) {
//		return dao.selectList(vo);
//	}
//	
//	public int countOrdersByMemberSeq(String user_seq) {
//		return dao.countOrdersByMemberSeq(user_seq);
//	}
//	
//	public List<OrderDto> findOrdersByMemberSeq(String user_seq, PageVo pageVo) {
//		return dao.findOrdersByMemberSeq(user_seq, pageVo);
//	}

}
