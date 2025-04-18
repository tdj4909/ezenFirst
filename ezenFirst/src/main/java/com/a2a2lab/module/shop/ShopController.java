package com.a2a2lab.module.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.a2a2lab.module.member.MemberService;
import com.a2a2lab.module.product.ProductService;
import com.a2a2lab.module.product.ProductVo;
import com.a2a2lab.module.review.ReviewService;
import jakarta.servlet.http.HttpSession;


@Controller
public class ShopController {

//	@Autowired
//	ShopService service;
	@Autowired
	MemberService memberService;
	@Autowired
	ProductService productService;
	@Autowired
	ReviewService reviewService;
	
	// 상품 리스트 화면
	@RequestMapping(value = "/TableOrder/shopList")
	public String shopList(Model model, HttpSession httpSession, @ModelAttribute("vo") ProductVo vo) {
		
		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/TableOrder/accountLogin";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
		
		vo.setRowNumToShow(6);
		vo.setParamsPaging(productService.selectOneCount(vo));
		
		model.addAttribute("list", productService.selectShopList(vo));
		
		return "/usr/shop/shopList";
	}
	
	// 상품 상세 화면
	@RequestMapping(value = "/TableOrder/shopDetail")
	public String shopDetail(@RequestParam("formSeq") String seq, Model model, HttpSession httpSession) {
		
		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/TableOrder/accountLogin";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
		
		ProductVo vo = new ProductVo();
		vo.setIfcgSeq(seq);
		model.addAttribute("item", productService.selectOne(vo));
		
		model.addAttribute("reviewList", reviewService.getReviewListByMenuSeq(seq));
		
		return "/usr/shop/shopDetail";
	}
	
}