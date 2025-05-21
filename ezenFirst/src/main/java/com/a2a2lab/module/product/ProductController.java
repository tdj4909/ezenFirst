package com.a2a2lab.module.product;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.a2a2lab.common.config.CustomUserDetails;
import com.a2a2lab.module.code.CodeService;
import com.a2a2lab.module.order.OrderService;
import com.a2a2lab.module.upload.UploadService;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {

	@Autowired
	ProductService service;
	@Autowired
	CodeService codeService;
	@Autowired
	UploadService uploadService;
	@Autowired
	OrderService orderService;
	
	
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
		if(file != null && !file.isEmpty()) {
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
	
	
//	************************************************************
//	사용자
//	************************************************************
	
	// 메뉴 리스트 화면
	@RequestMapping("/tableOrder/shop/list")
	public String showShopList(Model model, HttpSession session, Authentication auth) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("session : " + session);
		System.out.println("auth : " + auth);
		System.out.println("session ID : " + session.getId());
		return "usr/shop/shopList";
	}
	// 메뉴 리스트 Ajax
	@GetMapping("/tableOrder/shop/menuList")
	public String getMenuListFragment(@RequestParam(name = "page", defaultValue = "1") int page, Model model, PageVo pageVo, SearchVo searchVo) {
		
		pageVo.setRowNumToShow(6);
		pageVo.setThisPage(page);
		pageVo.setParamsPaging(service.countProductsByVo(pageVo, searchVo));
		
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("list", service.findProductsByVo(pageVo, searchVo));
		
		return "usr/fragment/menu :: menuListFragment";
	}
	// 장바구니 Ajax
	@GetMapping("/tableOrder/shop/cart")
	public String getCartFragment(Model model, Authentication auth) {
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		model.addAttribute("list", orderService.findCartsByMemberId(userDetails.getMemberId()));
		return "usr/fragment/cart :: cartFragment";
	}
	
	// 메뉴 상세 화면
	@RequestMapping("/tableOrder/shop/detail/{id}")
	public String shopDetail(@PathVariable("id") String id, Model model) {
		model.addAttribute("item", service.findProductById(id));
		return "usr/shop/shopDetail";
	}
	
	
}