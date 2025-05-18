package com.a2a2lab.module.product;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.a2a2lab.module.code.CodeService;
import com.a2a2lab.module.upload.UploadService;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

@Controller
public class ProductController {

	@Autowired
	ProductService service;
	@Autowired
	CodeService codeService;
	@Autowired
	UploadService uploadService;
	
	
//	************************************************************
//	관리자
//	************************************************************
	
	// 메뉴 관리 화면
	@RequestMapping("/xdm/service/product/list")
	public String showProductManagement(Model model, PageVo pageVo, SearchVo searchVo) {
		// 검색 설정
		model.addAttribute("searchVo", searchVo);
		// 페이징 설정
		pageVo.setParamsPaging(service.countProductsByVo(pageVo, searchVo));
		model.addAttribute("pageVo", pageVo);
		// 메뉴 종류
		model.addAttribute("codeList", codeService.findCodesByCodeGroupId("2"));
		
		model.addAttribute("list", service.findProductsByVo(pageVo, searchVo));
		
		return "xdm/product/productList";
	}
	
	// 메뉴 등록/수정 화면
	@RequestMapping("/xdm/service/product/edit")
	public String showProductEdit(Model model, @RequestParam("productId") String productId){
		// 메뉴 종류
		model.addAttribute("codeList", codeService.findCodesByCodeGroupId("2"));
		// productId가 있으면 수정, 없으면 등록
		if (!productId.equals("") && !productId.equals("0")) {
			model.addAttribute("item", service.findProductById(productId));
		}
		return "xdm/product/productEdit";
	}
	
	// 메뉴 추가
	@RequestMapping("/xdm/service/product/create")
	public String createProduct(@RequestParam("file") MultipartFile file, ProductDto dto) throws IOException {
		// File DB upload
		dto.setFileId(uploadService.localUpload(file));
		
		service.createProduct(dto);
		return "redirect:/xdm/service/product/list";
	}
	
	// 메뉴 수정
	@RequestMapping("/xdm/service/product/update")
	public String updateProduct(@RequestParam("file") MultipartFile file, ProductDto dto) throws IOException {
		// 파일 변경했을 때
		if(file != null) {
			// File DB upload
			dto.setFileId(uploadService.localUpload(file));
			// 파일만 Update
			service.fileUpdate(dto);
		}
		// 메뉴 갱신
		service.updateProduct(dto);
		return "redirect:/xdm/service/product/list";
	}
	
	// 메뉴 softDelete
	@RequestMapping("/xdm/service/product/softDelete")
	public String softDeleteProduct(@RequestParam("delSeq") List<String> idList) {
		for(String id : idList) {
			if(!id.equals("")) {
				service.softDeleteProduct(id);
			}
		}
		return "redirect:/xdm/service/product/list";
	}
	
	// 메뉴 hardDelete
	@RequestMapping("/xdm/service/product/hardDelete")
	public String hardDeleteProduct(@RequestParam("delSeq") List<String> idList) {
		for(String id : idList) {
			if(!id.equals("")) {
				service.hardDeleteProduct(id);
			}
		}
		return "redirect:/xdm/service/product/list";
	}
	
	
//	@RequestMapping(value = "/Xdm/productXdmList")
//	public String productXdmList(Model model, @ModelAttribute("vo") ProductVo vo, HttpSession httpSession) {
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
//		return "/xdm/product/productXdmList";
//	}
//	
//	// 메뉴등록 화면
//	@RequestMapping(value = "/Xdm/productXdmRegister")
//	public String productXdmRegister(@ModelAttribute("vo") ProductVo vo, Model model, HttpSession httpSession) {
//
//		// login 검사
//		if(httpSession.getAttribute("user") == null) {
//			return "redirect:/Xdm/loginXdm";
//		}
//		model.addAttribute("user", httpSession.getAttribute("user"));
//		
//		if (vo.getIfcgSeq().equals("0") || vo.getIfcgSeq().equals("")) {
////			insert mode
//		} else {
////			update mode
//			model.addAttribute("item", service.selectOne(vo));
//		}
//		
//		return "/xdm/product/productXdmRegister";
//	}
//	
//	
//	
//	// 메뉴 등록
//	@RequestMapping(value = "/Xdm/productXdmInst")
//	public String productXdmInst(@RequestParam("file") MultipartFile file, ProductDto dto) throws IOException {
//		
//		// File DB upload
//		dto.setFileUploaded_seq(uploadService.localUpload(file));
//		// 메뉴 등록
//		service.insert(dto);
//		
//		return "redirect:/Xdm/productXdmList";
//	}
//	
//	// 메뉴 갱신
//	@RequestMapping(value = "/Xdm/productXdmUpdt")
//	public String productXdmUpdt(@RequestParam("file") MultipartFile file, ProductDto dto) throws IOException {
//		
//		// 파일 변경했을 때
//		if(file != null) {
//			// File DB upload
//			dto.setFileUploaded_seq(uploadService.localUpload(file));
//			// 파일만 Update
//			service.fileUpdate(dto);
//		}
//		// 메뉴 갱신
//		service.update(dto);
//		
//		return "redirect:/Xdm/productXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/productXdmUele")
//	public String productXdmUele(@RequestParam("seq") List<String> seqs) {
//		
//		for(String seq : seqs) {
//			if(!seq.isBlank()) {
//				ProductDto dto = new ProductDto();
//				dto.setSeq(seq);
//				service.uelete(dto);
//			}
//		}
//		return "redirect:/Xdm/productXdmList";
//	}
//	
//	@RequestMapping(value = "/Xdm/productXdmDele")
//	public String productXdmDele(@RequestParam("seq") List<String> seqs) {
//		
//		for(String seq : seqs) {
//			if(!seq.isBlank()) {
//				ProductDto dto = new ProductDto();
//				dto.setSeq(seq);
//				service.delete(dto);
//			}
//		}
//		return "redirect:/Xdm/productXdmList";
//	}
	
	
}