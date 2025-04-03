package com.a2a2lab.module.orders;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

	@Autowired
	OrdersDao dao;
	
	public int uelete(OrdersDto dto) {
		return dao.uelete(dto);
	}
	
	public int delete(OrdersDto dto) {
		return dao.delete(dto);
	}
	
	public int update(OrdersDto dto) {
		return dao.update(dto);
	}

	public int selectOneCount(OrdersVo vo) {
		return dao.selectOneCount(vo);
	}
	
	public int ordersCount() {
		return dao.ordersCount();
	}
	
	public int ordersSum() {
		return dao.ordersSum();
	}
	
	public OrdersDto selectOne(OrdersDto dto) {
		return dao.selectOne(dto);
	}
	
	public List<OrdersDto> selectOneList(OrdersDto dto) {
		return dao.selectOneList(dto);
	}
	
	public List<OrdersDto> selectList(OrdersVo vo) {
		return dao.selectList(vo);
	}

}
