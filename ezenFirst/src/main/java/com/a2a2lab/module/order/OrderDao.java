package com.a2a2lab.module.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Repository
public interface OrderDao {
	
	// 관리자 주문관리
	public int countOrderMastersByVo(SearchVo searchVo);
	public List<OrderDto> findOrderMastersByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo);
	
	// 사용자 주문 내역
	public int countOrderMastersByMemberId(String memberId);
	public List<OrderDto> findOrderMastersByMemberId(@Param("pageVo") PageVo pageVo, @Param("memberId") String memberId);
	
	// 주문 상세
	public OrderDto findOrderMasterById(String orderMasterId);
	public List<OrderDto> findOrderDetailsByOrderMasterId(String orderMasterId);
	
	// 관리자 index
	public Integer countAllOrder();
	public Integer sumAllOrder();
	
	// Select를 제외한 CRUD
	public int saveOrderMaster(OrderDto dto);
	public int saveOrderDetail(OrderDto dto);
	public int updateOrderMaster(OrderDto dto);
	public int softDeleteOrderMaster(String orderMasterId);
	public int hardDeleteOrderMaster(String orderMasterId);
	
}
