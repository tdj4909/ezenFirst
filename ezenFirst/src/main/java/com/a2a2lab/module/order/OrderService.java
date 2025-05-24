package com.a2a2lab.module.order;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.code.CodeService;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Service
public class OrderService {

	@Autowired
	OrderDao dao;
	
	@Autowired
	CodeService codeService;
	
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
		// 캐싱된 코드 맵에서 주문 상태 종류 코드그룹 가져오기
	    List<CodeDto> codeDtos = codeService.getCodesByCodegroupName("주문 상태");

	    // codeId → name 맵으로 변환
	    Map<Integer, String> codeNameMap = new HashMap<>();
	    for (CodeDto codeDto : codeDtos) {
	        codeNameMap.put(Integer.parseInt(codeDto.getCodeId()), codeDto.getName());
	    }

	    // OrderMaster 목록 가져오기
	    List<OrderDto> dtos = dao.findOrderMastersByMemberId(pageVo, memberId);

	    // 주문 상태에 해당하는 이름 설정
	    for (OrderDto dto : dtos) {
	        dto.setStatusName(codeNameMap.get(dto.getStatus()));
	    }

	    return dtos;
	}
	
	public OrderDto findOrderMasterById(String id) {
		// 캐싱된 코드 맵에서 주문 상태 종류 코드그룹 가져오기
	    List<CodeDto> codeDtos = codeService.getCodesByCodegroupName("주문 상태");

	    // codeId → name 맵으로 변환
	    Map<Integer, String> codeNameMap = new HashMap<>();
	    for (CodeDto codeDto : codeDtos) {
	        codeNameMap.put(Integer.parseInt(codeDto.getCodeId()), codeDto.getName());
	    }

	    // OrderMaster 가져오기
	    OrderDto dto = dao.findOrderMasterById(id);
	    
	    // 주문 상태에 해당하는 이름 설정
	    dto.setStatusName(codeNameMap.get(dto.getStatus()));

	    return dto;
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
