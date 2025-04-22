package com.a2a2lab.module.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao {
	
	
	
	public int insert(ProductDto dto);
	public int update(ProductDto dto);
	public int fileUpdate(ProductDto dto);
	public int uelete(ProductDto dto);
	public int delete(ProductDto dto);
	public int selectOneCount(ProductVo vo);
	public ProductDto selectOne(ProductVo vo);
	public List<ProductDto> selectList(ProductVo vo);
	public List<ProductDto> selectShopList(ProductVo vo);

}
