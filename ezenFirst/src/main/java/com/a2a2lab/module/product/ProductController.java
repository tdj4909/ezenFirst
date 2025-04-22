package com.a2a2lab.module.product;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.a2a2lab.module.upload.UploadService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {

	@Autowired
	ProductService service;
	@Autowired
	UploadService uploadService;
	
	
	@RequestMapping(value = "/Xdm/productXdmList")
	public String productXdmList(Model model, @ModelAttribute("vo") ProductVo vo, HttpSession httpSession) {

		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/Xdm/loginXdm";
		}
		model.addAttribute("user", httpSession.getAttribute("user"));
		
		vo.setParamsPaging(service.selectOneCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("lists", service.selectList(vo));
		}
		
		return "/xdm/product/productXdmList";
	}
	
	// 메뉴등록 화면
	@RequestMapping(value = "/Xdm/productXdmRegister")
	public String productXdmRegister(@ModelAttribute("vo") ProductVo vo, Model model, HttpSession httpSession) {

		// login 검사
		if(httpSession.getAttribute("user") == null) {
			return "redirect:/Xdm/loginXdm";
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
	
	
	
	// 메뉴 등록
	@RequestMapping(value = "/Xdm/productXdmInst")
	public String productXdmInst(@RequestParam("file") MultipartFile file, ProductDto dto) throws IOException {
		
		// File DB upload
		dto.setFileUploaded_seq(uploadService.localUpload(file));
		// 메뉴 등록
		service.insert(dto);
		
		return "redirect:/Xdm/productXdmList";
	}
	
	// 메뉴 갱신
	@RequestMapping(value = "/Xdm/productXdmUpdt")
	public String productXdmUpdt(@RequestParam("file") MultipartFile file, ProductDto dto) throws IOException {
		
		// 파일 변경했을 때
		if(file != null) {
			// File DB upload
			dto.setFileUploaded_seq(uploadService.localUpload(file));
			// 파일만 Update
			service.fileUpdate(dto);
		}
		// 메뉴 갱신
		service.update(dto);
		
		return "redirect:/Xdm/productXdmList";
	}
	
	@RequestMapping(value = "/Xdm/productXdmUele")
	public String productXdmUele(@RequestParam("seq") List<String> seqs) {
		
		for(String seq : seqs) {
			if(!seq.isBlank()) {
				ProductDto dto = new ProductDto();
				dto.setSeq(seq);
				service.uelete(dto);
			}
		}
		return "redirect:/Xdm/productXdmList";
	}
	
	@RequestMapping(value = "/Xdm/productXdmDele")
	public String productXdmDele(@RequestParam("seq") List<String> seqs) {
		
		for(String seq : seqs) {
			if(!seq.isBlank()) {
				ProductDto dto = new ProductDto();
				dto.setSeq(seq);
				service.delete(dto);
			}
		}
		return "redirect:/Xdm/productXdmList";
	}
	
	
}