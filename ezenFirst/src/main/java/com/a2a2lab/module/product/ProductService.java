package com.a2a2lab.module.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.code.CodeDao;
import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.code.CodeService;
import com.a2a2lab.module.member.MemberDto;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Service
public class ProductService {

	@Autowired
	ProductDao dao;
	@Autowired
	CodeService codeService;

	public int countProductsByVo(PageVo pageVo, SearchVo searchVo) {
		return dao.countProductsByVo(pageVo, searchVo);
	}
	
	public List<ProductDto> findProductsByVo(PageVo pageVo, SearchVo searchVo) {
		// 1. 캐싱된 코드 맵에서 메뉴종류 코드 그룹("2") 가져오기
	    List<CodeDto> codeDtos = codeService.getCodesByCodegroupId("2");

	    // 2. codeId → name 맵으로 변환
	    Map<Integer, String> codeNameMap = new HashMap<>();
	    for (CodeDto codeDto : codeDtos) {
	        codeNameMap.put(Integer.parseInt(codeDto.getCodeId()), codeDto.getName());
	    }

	    // 3. 메뉴 목록 가져오기
	    List<ProductDto> dtos = dao.findProductsByVo(pageVo, searchVo);

	    // 4. 각 메뉴의 메뉴종류 코드에 해당하는 이름 설정
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
	
//
//	public List<ProductDto> selectList(ProductVo vo) {
//		List<ProductDto> dtos = dao.selectList(vo);
//		for(ProductDto dto : dtos) {
//			if (dto.getMenuType() == 1) {
//				dto.setMenuTypeNm("세트");
//			} else if (dto.getMenuType() == 2) {
//				dto.setMenuTypeNm("단품");
//			} else if (dto.getMenuType() == 3) {
//				dto.setMenuTypeNm("사이드");
//			} else if (dto.getMenuType() == 4) {
//				dto.setMenuTypeNm("음료");
//			}
//		}
//		
//		return dtos;
//	}
//	
//	public List<ProductDto> selectShopList(ProductVo vo) {
//		return dao.selectShopList(vo);
//	}

}
