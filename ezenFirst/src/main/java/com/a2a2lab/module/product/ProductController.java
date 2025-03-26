package com.a2a2lab.module.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.codeGroup.CodeGroupDto;

@Controller
public class ProductController {

	@Autowired
	ProductService service;
	
	
	@RequestMapping(value = "/productXdmList")
	public String productXdmList(Model model, @ModelAttribute("vo") ProductVo vo) {

		vo.setParamsPaging(service.selectOneCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("lists", service.selectList(vo));
		}
		
		return "/xdm/product/productXdmList";
	}
	
	
}