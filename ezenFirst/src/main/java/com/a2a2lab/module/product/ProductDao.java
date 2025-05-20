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
	
	
	
	
//	public int insert(ProductDto dto);
//	public int update(ProductDto dto);
//	public int updateRating(ProductDto dto);
//	public int fileUpdate(ProductDto dto);
//	public int uelete(ProductDto dto);
//	public int delete(ProductDto dto);
//	public int selectOneCount(ProductVo vo);
//	public ProductDto selectOne(ProductVo vo);
//	public List<ProductDto> selectList(ProductVo vo);
//	public List<ProductDto> selectShopList(ProductVo vo);

}
