package com.a2a2lab.module.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.a2a2lab.module.product.ProductService;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

import jakarta.servlet.http.HttpSession;

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
	
	
//	@RequestMapping(value = "/Xdm/reviewXdmList")
//	public String reviewXdmList(@ModelAttribute("vo") ReviewVo vo, Model model, HttpSession httpSession) {
//		
//		// login 검사
//		if(httpSession.getAttribute("user") == null) {
//			return "redirect:/Xdm/loginXdm";
//		}
//		model.addAttribute("user", httpSession.getAttribute("user"));
//				
//		vo.setParamsPaging(service.selectOneCount(vo));
//	
//		if (vo.getTotalRows() > 0) {
//			model.addAttribute("lists", service.selectList(vo));
//		}
//	
//		return "/xdm/review/reviewXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/reviewXdmUele")
//	public String reviewXdmUele(@RequestParam("seq") List<String> seqs) {
//		
//		for(String seq : seqs) {
//			if(!seq.isBlank()) {
//				ReviewDto dto = new ReviewDto();
//				dto.setSeq(seq);
//				service.uelete(dto);
//			}
//		}
//		return "redirect:/Xdm/reviewXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/reviewXdmDele")
//	public String reviewXdmDele(@RequestParam("seq") List<String> seqs) {
//		
//		for(String seq : seqs) {
//			if(!seq.isBlank()) {
//				ReviewDto dto = new ReviewDto();
//				dto.setSeq(seq);
//				service.delete(dto);
//			}
//		}
//		return "redirect:/Xdm/reviewXdmList";
//	}
	
//	// 리뷰 저장
//	@RequestMapping("/TableOrder/reviewInst")
//	@ResponseBody
//	public ReviewDto reviewInst(@RequestBody ReviewDto dto) {
//		
//		dto.setReviewDate(LocalDate.now().toString());
//		service.insert(dto);
//		ProductDto productDto = new ProductDto();
//		productDto.setSeq(dto.getMenu_seq());
//		productDto.setMenuRating(service.findAvgRatingByMenuSeq(dto.getMenu_seq()));
////		productService.updateRating(productDto);
//
//	    return dto;
//	}
	
//	// 리뷰 Ajax
//	@GetMapping("/TableOrder/reviewFragment")
//	public String reviewFragment(@RequestParam("seq") String seq, Model model, HttpSession httpSession) {
//		
//		// login 검사
//		if(httpSession.getAttribute("user") == null) {
//			return "redirect:/TableOrder/accountLogin";
//		}
//		model.addAttribute("user", httpSession.getAttribute("user"));
//		
//		model.addAttribute("reviewList", service.getReviewListByMenuSeq(seq));
//		return "/usr/shop/reviewFragment :: reviewFragment";
//	}
}