package com.a2a2lab.module.orders;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.codeGroup.CodeGroupDto;
import com.a2a2lab.module.codeGroup.CodeGroupVo;

@Repository
public interface OrdersDao {
	
	public String insertOrder(String user_seq);
	public int uelete(OrdersDto dto);
	public int delete(OrdersDto dto);
	public int update(OrdersDto dto);
	public int selectOneCount(OrdersVo vo);
	public int ordersCount();
	public int ordersSum();
	public OrdersDto selectOne(OrdersDto dto);
	public List<OrdersDto> selectOneList(OrdersDto dto);
	public List<OrdersDto> selectList(OrdersVo vo);

}
