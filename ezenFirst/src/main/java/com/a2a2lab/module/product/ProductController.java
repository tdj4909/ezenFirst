package com.a2a2lab.module.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.code.CodeVo;
import com.a2a2lab.module.codeGroup.CodeGroupDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {

	@Autowired
	ProductService service;
	
	
	@RequestMapping(value = "/productXdmList")
	public String productXdmList(Model model, @ModelAttribute("vo") ProductVo vo, HttpSession httpSession) {

		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/login";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
		
		vo.setParamsPaging(service.selectOneCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("lists", service.selectList(vo));
		}
		
		return "/xdm/product/productXdmList";
	}
	
	@RequestMapping(value = "/productXdmRegister")
	public String productXdmRegister(@ModelAttribute("vo") ProductVo vo, Model model, HttpSession httpSession) {

		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/login";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
		
		if (vo.getIfcgSeq().equals("0") || vo.getIfcgSeq().equals("")) {
//			insert mode
		} else {
//			update mode
			model.addAttribute("item", service.selectOne(vo));
		}
		
		return "/xdm/product/productXdmRegister";
	}
	
	@RequestMapping(value = "/productXdmInst")
	public String productXdmInst(ProductDto dto) {
		service.insert(dto);
		return "redirect:/productXdmList";
	}
	
	
}