package com.a2a2lab.module.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Repository
public interface ProductDao {
	
	// 관리자 메뉴 관리
	public int countProductsByVo(@Param("searchVo") SearchVo searchVo);
	public List<ProductDto> findProductsByVo(@Param("pageVo") PageVo pageVo, @Param("searchVo") SearchVo searchVo);
	
	// 메뉴 상세
	public ProductDto findProductById(String productId);
	
	// Select를 제외한 CRUD
	public int createProduct(ProductDto dto);
	public int updateProduct(ProductDto dto);
	public int softDeleteProduct(String productId);
	public int hardDeleteProduct(String productId);
	public int fileUpdate(ProductDto dto);
	public int updateRating(ProductDto dto);
	public int updateOrderCountByProductId(@Param("productId") String productId, @Param("orderCount") Integer orderCount);

}
