package com.a2a2lab.module.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Repository
public interface ProductDao {
	
	public int countProductsByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo); // vo로 메뉴 개수 검색
	public List<ProductDto> findProductsByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo); // vo로 메뉴 검색
	public ProductDto findProductById(String productId);
	public int createProduct(ProductDto dto);
	public int updateProduct(ProductDto dto);
	public int softDeleteProduct(String productId);
	public int hardDeleteProduct(String productId);
	public int fileUpdate(ProductDto dto);
	public int updateRating(ProductDto dto);
	public int updateOrderCountByProductId(String productId);

}
