package com.a2a2lab.module.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	ProductDao dao;
	
	public int insert(ProductDto dto) {
		return dao.insert(dto);
	}
	
	public int uelete(ProductDto dto) {
		return dao.uelete(dto);
	}
	
	public int delete(ProductDto dto) {
		return dao.delete(dto);
	}
	
	public int update(ProductDto dto) {
		return dao.update(dto);
	}

	public int selectOneCount(ProductVo vo) {
		return dao.selectOneCount(vo);
	}
	
	public ProductDto selectOne(ProductVo vo) {
		return dao.selectOne(vo);
	}

	public List<ProductDto> selectList(ProductVo vo) {
		List<ProductDto> dtos = dao.selectList(vo);
		for(ProductDto dto : dtos) {
			if (dto.getMenuType() == 1) {
				dto.setMenuTypeNm("세트");
			} else if (dto.getMenuType() == 2) {
				dto.setMenuTypeNm("단품");
			} else if (dto.getMenuType() == 3) {
				dto.setMenuTypeNm("사이드");
			} else if (dto.getMenuType() == 4) {
				dto.setMenuTypeNm("음료");
			}
		}
		
		return dtos;
	}
	
	public List<ProductDto> selectShopList(ProductVo vo) {
		return dao.selectShopList(vo);
	}

}
