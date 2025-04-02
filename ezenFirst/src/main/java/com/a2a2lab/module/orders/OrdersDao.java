package com.a2a2lab.module.orders;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.codeGroup.CodeGroupDto;
import com.a2a2lab.module.codeGroup.CodeGroupVo;

@Repository
public interface OrdersDao {
	
	public int uelete(OrdersDto dto);
	public int delete(OrdersDto dto);
	public int update(OrdersDto dto);
	public int selectOneCount(OrdersVo vo);
	public OrdersDto selectOne(OrdersDto dto);
	public List<OrdersDto> selectList(OrdersVo vo);

}
