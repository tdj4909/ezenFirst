package com.a2a2lab.module.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.codeGroup.CodeGroupDto;
import com.a2a2lab.module.codeGroup.CodeGroupVo;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService {

	@Autowired
	ProductDao dao;
	
	public int insert(ProductDto dto) {
		return dao.insert(dto);
	}

	public int selectOneCount(ProductVo vo) {
		return dao.selectOneCount(vo);
	}

	public List<ProductDto> selectList(ProductVo vo) {
		return dao.selectList(vo);
	}

}
