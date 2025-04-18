package com.a2a2lab.module.review;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewController{
	
	@Autowired
	ReviewService service;
	
	@RequestMapping(value = "/Xdm/reviewXdmList")
	public String reviewXdmList(@ModelAttribute("vo") ReviewVo vo, Model model, HttpSession httpSession) {
		
		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/Xdm/loginXdm";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
				
		vo.setParamsPaging(service.selectOneCount(vo));
	
		if (vo.getTotalRows() > 0) {
			model.addAttribute("lists", service.selectList(vo));
		}
	
		return "/xdm/review/reviewXdmList";
	}
	
	@RequestMapping(value = "/Xdm/reviewXdmUele")
	public String reviewXdmUele(@RequestParam("seq") List<String> seqs) {
		
		for(String seq : seqs) {
			if(!seq.isBlank()) {
				ReviewDto dto = new ReviewDto();
				dto.setSeq(seq);
				service.uelete(dto);
			}
		}
		return "redirect:/Xdm/reviewXdmList";
	}
	
	@RequestMapping(value = "/Xdm/reviewXdmDele")
	public String reviewXdmDele(@RequestParam("seq") List<String> seqs) {
		
		for(String seq : seqs) {
			if(!seq.isBlank()) {
				ReviewDto dto = new ReviewDto();
				dto.setSeq(seq);
				service.delete(dto);
			}
		}
		return "redirect:/Xdm/reviewXdmList";
	}
	
	// 리뷰 저장
	@RequestMapping("/review/save")
	@ResponseBody
	public ReviewDto saveReview(@RequestBody ReviewDto reviewDto) {
		
		reviewDto.setReviewDate(LocalDate.now().toString());
		service.insert(reviewDto);

	    return reviewDto;
	}
}