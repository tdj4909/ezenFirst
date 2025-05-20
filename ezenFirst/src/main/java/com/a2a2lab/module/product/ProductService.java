package com.a2a2lab.module.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.code.CodeDao;
import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Service
public class ProductService {

	@Autowired
	ProductDao dao;
	@Autowired
	CodeDao codeDao;

	public int countProductsByVo(PageVo pageVo, SearchVo searchVo) {
		return dao.countProductsByVo(pageVo, searchVo);
	}
	
	public List<ProductDto> findProductsByVo(PageVo pageVo, SearchVo searchVo) {
		// 메뉴 종류 이름 세팅
		Map<Integer, String> map = new HashMap<>();
		List<CodeDto> codeDtos= codeDao.findCodesByCodeGroupId("2");
		for(CodeDto codeDto : codeDtos) {
			map.put(Integer.parseInt(codeDto.getCodeId()), codeDto.getName());
		}
		
		List<ProductDto> dtos = dao.findProductsByVo(pageVo, searchVo);
		for(ProductDto dto : dtos) {
			dto.setTypeName(map.get(dto.getType()));
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
	
//	public int insert(ProductDto dto) {
//		return dao.insert(dto);
//	}
//	
//	public int uelete(ProductDto dto) {
//		return dao.uelete(dto);
//	}
//	
//	public int delete(ProductDto dto) {
//		return dao.delete(dto);
//	}
//	
//	public int update(ProductDto dto) {
//		return dao.update(dto);
//	}
//	
//	public int updateRating(ProductDto dto) {
//		return dao.updateRating(dto);
//	}
//	
//	public int fileUpdate(ProductDto dto) {
//		return dao.fileUpdate(dto);
//	}
//
//	public int selectOneCount(ProductVo vo) {
//		return dao.selectOneCount(vo);
//	}
//	
//	public ProductDto selectOne(ProductVo vo) {
//		return dao.selectOne(vo);
//	}
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
