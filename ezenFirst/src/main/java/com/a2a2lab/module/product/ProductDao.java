package com.a2a2lab.module.product;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.codeGroup.CodeGroupDto;
import com.a2a2lab.module.codeGroup.CodeGroupVo;

@Repository
public interface ProductDao {
	
	
	
	public int insert(ProductDto dto);
	public int update(ProductDto dto);
	public int uelete(ProductDto dto);
	public int selectOneCount(ProductVo vo);
	public ProductDto selectOne(ProductVo vo);
	public List<ProductDto> selectList(ProductVo vo);

}
