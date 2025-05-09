package com.a2a2lab.module.orders;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.codeGroup.CodeGroupDto;
import com.a2a2lab.module.codeGroup.CodeGroupVo;
import com.a2a2lab.module.vo.PageVo;

@Repository
public interface OrdersDao {
	
	public int insertOrder(OrdersDto dto);
	public int insertOrderMenu(OrdersDto dto);
	public int uelete(OrdersDto dto);
	public int delete(OrdersDto dto);
	public int update(OrdersDto dto);
	public int selectOneCount(OrdersVo vo);
	public int ordersCount();
	public int ordersSum();
	public OrdersDto selectOne(OrdersDto dto);
	public List<OrdersDto> selectOneList(OrdersDto dto);
	public List<OrdersDto> selectList(OrdersVo vo);
	
	public int countOrdersByMemberSeq(String user_seq);
	public List<OrdersDto> findOrdersByMemberSeq(@Param("user_seq") String user_seq, @Param("pageVo") PageVo pageVo);

}
