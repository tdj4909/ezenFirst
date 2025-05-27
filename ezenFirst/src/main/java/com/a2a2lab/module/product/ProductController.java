package com.a2a2lab.module.product;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.a2a2lab.module.code.CodeDto;
import com.a2a2lab.module.code.CodeService;
import com.a2a2lab.module.upload.UploadService;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

import jakarta.servlet.http.HttpServletResponse;

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
		pageVo.setParamsPaging(service.countProductsByVo(searchVo));
		model.addAttribute("pageVo", pageVo);
		// 메뉴 종류
		model.addAttribute("codeList", codeService.getCodesByCodegroupName("메뉴 종류"));
		// 메뉴 출력
		model.addAttribute("list", service.findProductsByVo(pageVo, searchVo));
		return "xdm/product/productList";
	}
	// 메뉴 등록/수정 화면
	@RequestMapping("/xdm/service/product/edit")
	public String showProductEdit(Model model, @RequestParam("productId") String productId){
		// 메뉴 종류
		model.addAttribute("codeList", codeService.getCodesByCodegroupName("메뉴 종류"));
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
		// 메뉴 추가
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
	// Excel 다운로드
	@RequestMapping("/xdm/service/product/excel/download")
	public void downloadProductExcel(HttpServletResponse response, PageVo pageVo, SearchVo searchVo) throws IOException {
		pageVo.setParamsPaging(service.countProductsByVo(searchVo));
		List<ProductDto> products = service.findProductsByVo(pageVo, searchVo); // 필터링 적용된 목록

	    // 엑셀 워크북 생성
	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("Products");

	    // 헤더
	    Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue("메뉴 번호");
	    header.createCell(1).setCellValue("종류");
	    header.createCell(2).setCellValue("메뉴명");
	    header.createCell(3).setCellValue("가격");
	    header.createCell(4).setCellValue("열량(kcal)");
	    header.createCell(5).setCellValue("평점");
	    header.createCell(6).setCellValue("누적 주문 수");
	    header.createCell(7).setCellValue("등록일");
	    header.createCell(8).setCellValue("수정일");
	    header.createCell(9).setCellValue("추천 여부");

	    // 내용
	    int rowNum = 1;
	    for (ProductDto product : products) {
	        Row row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue(product.getProductId());
	        row.createCell(1).setCellValue(product.getTypeName());
	        row.createCell(2).setCellValue(product.getName());
	        row.createCell(3).setCellValue(product.getPrice());
	        row.createCell(4).setCellValue(product.getCalories());
	        row.createCell(5).setCellValue(product.getRating() == null ? "평점 없음" : String.valueOf(product.getRating()));
	        row.createCell(6).setCellValue(product.getOrderCount());
	        row.createCell(7).setCellValue(product.getCreatedAt());
	        row.createCell(8).setCellValue(product.getUpdatedAt() == null ? "" : product.getUpdatedAt());
	        row.createCell(9).setCellValue(product.getIsRecommand() == 1 ? "Y" : "N");
	    }
	    
	    // 열 너비 자동 조절
	    for (int i = 0; i < 10; i++) {
	        sheet.autoSizeColumn(i);
	        sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1024); // 약간 여유 (한글 대응)
	    }
	    
	    // 응답 설정
	    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	    response.setHeader("Content-Disposition", "attachment; filename=products.xlsx");

	    // 엑셀 파일 내보내기
	    workbook.write(response.getOutputStream());
	    workbook.close();
	}
	
//	************************************************************
//	사용자
//	************************************************************
	// 메뉴 리스트 화면
	@RequestMapping("/tableOrder/shop/list")
	public String showShopList(@RequestParam(name = "recommand", required = false) String recommand,
            				   @RequestParam(name = "type", required = false) String type,
            				   Model model) {
		model.addAttribute("recommand", recommand);
	    model.addAttribute("type", type);
		return "usr/shop/shopList";
	}
	// 메뉴 리스트 Ajax
	@RequestMapping("/tableOrder/shop/menuList")
	public String getMenuListFragment(@RequestParam(name = "page", defaultValue = "1") Integer page,
									  @RequestParam(name = "shValue", defaultValue = "") String shValue,
									  @RequestParam(name = "type", defaultValue = "") String type,
									  @RequestParam(name = "recommand", defaultValue = "") Integer recommand,
									  Model model, 
									  PageVo pageVo, 
									  SearchVo searchVo) {
		// 검색 설정		
		searchVo.setShOption(2);
		List<CodeDto> codeDtos = codeService.getCodesByCodegroupName("메뉴 종류");
		for(CodeDto codeDto : codeDtos) {
			System.out.println(codeDto.getName());
			if(codeDto.getName().equals(type)) {
				searchVo.setShType(Integer.parseInt(codeDto.getCodeId()));
				break;
			}
		}
		searchVo.setShRecommand(recommand);
		searchVo.setShValue(shValue);
		model.addAttribute("searchVo", searchVo); 
		// 페이징 세팅
		pageVo.setRowNumToShow(6);
		pageVo.setThisPage(page);
		pageVo.setParamsPaging(service.countProductsByVo(searchVo));
		model.addAttribute("pageVo", pageVo);
		// 메뉴 출력
		model.addAttribute("list", service.findProductsByVo(pageVo, searchVo));
		return "usr/fragment/menu :: menuListFragment";
	}
	// 메뉴 상세 화면
	@RequestMapping("/tableOrder/shop/detail/{id}")
	public String shopDetail(@PathVariable("id") String id, Model model) {
		model.addAttribute("item", service.findProductById(id));
		return "usr/shop/shopDetail";
	}
	
}