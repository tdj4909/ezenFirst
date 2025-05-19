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
	public int softDeleteOrderMaster(String orderMasterId);
	public int hardDeleteOrderMaster(String orderMasterId);
	
//	public int insertOrder(OrderDto dto);
//	public int insertOrderMenu(OrderDto dto);
//	public int uelete(OrderDto dto);
//	public int delete(OrderDto dto);
//	public int update(OrderDto dto);
//	public int selectOneCount(OrderVo vo);
//	public int ordersCount();
//	public int ordersSum();
//	public OrderDto selectOne(OrderDto dto);
//	public List<OrderDto> selectOneList(OrderDto dto);
//	public List<OrderDto> selectList(OrderVo vo);
//	
//	public int countOrdersByMemberSeq(String user_seq);
//	public List<OrderDto> findOrdersByMemberSeq(@Param("user_seq") String user_seq, @Param("pageVo") PageVo pageVo);

}
