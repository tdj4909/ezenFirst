package com.a2a2lab.module.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Repository
public interface OrderDao {
	
	public int countOrderMastersByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo); // vo로 주문 개수 검색
	public List<OrderDto> findOrderMastersByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo); // vo로 주문 검색
	public OrderDto findOrderMasterById(String orderMasterId);
	public List<OrderDto> findOrderDetailsByOrderMasterId(String orderMasterId);	
	public Integer countAllOrder();
	public Integer sumAllOrder();
	public int updateOrderMaster(OrderDto dto);
	public int softDeleteOrderMaster(String orderMasterId);
	public int hardDeleteOrderMaster(String orderMasterId);
	
}
