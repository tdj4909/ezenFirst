package com.a2a2lab.module.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a2a2lab.common.config.CustomUserDetails;
import com.a2a2lab.module.product.ProductDto;
import com.a2a2lab.module.product.ProductService;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Controller
public class ReviewController{
	
	@Autowired
	ReviewService service;
	@Autowired
	ProductService productService;
	
//	************************************************************
//	관리자
//	************************************************************
	// 리뷰 관리 화면
	@RequestMapping("/xdm/service/review/list")
	public String showReviewManagement(Model model, PageVo pageVo, SearchVo searchVo) {
		// 검색 설정
		model.addAttribute("searchVo", searchVo);
		// 페이징 설정
		pageVo.setParamsPaging(service.countReviewsByVo(pageVo, searchVo));
		model.addAttribute("pageVo", pageVo);
		// 리뷰 출력
		model.addAttribute("list", service.findReviewsByVo(pageVo, searchVo));
		return "xdm/review/reviewList";
	}
	// 리뷰 softDelete
	@RequestMapping("/xdm/service/review/softDelete")
	public String softDeleteReview(@RequestParam("delSeq") List<String> idList) {
		for(String id : idList) {
			if(!id.equals("")) {
				service.softDeleteReview(id);
			}
		}
		return "redirect:/xdm/service/review/list";
	}
	// 리뷰 hardDelete
	@RequestMapping("/xdm/service/review/hardDelete")
	public String hardDeleteReview(@RequestParam("delSeq") List<String> idList) {
		for(String id : idList) {
			if(!id.equals("")) {
				service.hardDeleteReview(id);
			}
		}
		return "redirect:/xdm/service/review/list";
	}	
	
//	************************************************************
//	사용자
//	************************************************************
	// 리뷰 저장
	@RequestMapping("/tableOrder/review/save")
	@ResponseBody
	public ReviewDto saveReview(@RequestBody ReviewDto dto) {
		// 리뷰 저장
		service.saveReview(dto);
		// 메뉴 평점 수정
		ProductDto productDto = new ProductDto();
		productDto.setProductId(dto.getProductId());
		productDto.setRating(service.findAvgRatingByProductId(dto.getProductId()));
		productService.updateRating(productDto);
	    return dto;
	}
	// 리뷰 Ajax
	@GetMapping("/tableOrder/review/fragment")
	public String reviewFragment(@RequestParam("productId") String productId,
								 Model model,
								 Authentication auth) {
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		String memberId = userDetails.getMemberId();
		String memberName = userDetails.getName();
		
		model.addAttribute("memberId", memberId);
		model.addAttribute("memberName", memberName);
		model.addAttribute("reviewList", service.getReviewListByProductId(productId));
		return "usr/fragment/review :: reviewFragment";
	}
	
}