package com.a2a2lab.module.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.cart.CartDao;
import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.code.CodeService;
import com.a2a2lab.module.order.OrderDao;
import com.a2a2lab.module.review.ReviewDao;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Service
public class ProductService {

	@Autowired
	ProductDao dao;
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	ReviewDao reviewDao;

	public int countProductsByVo(SearchVo searchVo) {
		return dao.countProductsByVo(searchVo);
	}
	
	public List<ProductDto> findProductsByVo(PageVo pageVo, SearchVo searchVo) {
		// 캐싱된 코드 맵에서 메뉴 종류 코드그룹 가져오기
	    List<CodeDto> codeDtos = codeService.getCodesByCodegroupName("메뉴 종류");

	    // codeId → name 맵으로 변환
	    Map<Integer, String> codeNameMap = new HashMap<>();
	    for (CodeDto codeDto : codeDtos) {
	        codeNameMap.put(Integer.parseInt(codeDto.getCodeId()), codeDto.getName());
	    }

	    // 메뉴 목록 가져오기
	    List<ProductDto> dtos = dao.findProductsByVo(pageVo, searchVo);

	    // 각 메뉴의 메뉴종류 코드에 해당하는 이름 설정
	    for (ProductDto dto : dtos) {
	        dto.setTypeName(codeNameMap.get(dto.getType()));
	    }

	    return dtos;
	}
	
	public ProductDto findProductById(String id) {
		return dao.findProductById(id);
	}
	
	public int createProduct(ProductDto dto) {
		return dao.createProduct(dto);
	}
	
	public int updateProduct(ProductDto dto) {
		return dao.updateProduct(dto);
	}
	
	public int softDeleteProduct(String id) {
		cartDao.softDeleteCartByProductId(id);
		orderDao.softDeleteOrderMasterByProductId(id);
		reviewDao.softDeleteReviewByProductId(id);
		
		return dao.softDeleteProduct(id);
	}
	
	public int hardDeleteProduct(String id) {
		return dao.hardDeleteProduct(id);
	}
	
	public int fileUpdate(ProductDto dto) {
		return dao.fileUpdate(dto);
	}
	
	public int updateRating(ProductDto dto) {
		return dao.updateRating(dto);
	}
	
	public int updateOrderCountByProductId(String productId, Integer orderCount) {
		return dao.updateOrderCountByProductId(productId, orderCount);
	}

}
