package com.a2a2lab.module.code;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.a2a2lab.module.codeGroup.CodeGroupDto;
import com.a2a2lab.module.codeGroup.CodeGroupService;
import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CodeController {

	@Autowired
	CodeService service;
	
	@Autowired
	CodeGroupService codeGroupService;
	
	// 코드 관리 화면
	@RequestMapping("/xdm/system/code/list")
	public String showCodeManagement(Model model, PageVo pageVo, SearchVo searchVo) {
		// 검색 설정
		model.addAttribute("searchVo", searchVo);
		// 페이징 설정
		pageVo.setParamsPaging(service.countCodesByVo(pageVo, searchVo));
		model.addAttribute("pageVo", pageVo);
		// 코드 출력
		model.addAttribute("list", service.findCodesByVo(pageVo, searchVo));
		return "xdm/code/codeList";
	}
	// 코드 등록/수정 화면
	@RequestMapping("/xdm/system/code/edit")
	public String showCodeEdit(Model model, @RequestParam("codeId") String codeId){
		model.addAttribute("codegroupList", codeGroupService.findAllCodeGroup());
		// codeId가 있으면 수정, 없으면 등록
		if (!codeId.equals("") && !codeId.equals("0")) {
			model.addAttribute("item", service.findCodeById(codeId));
		}
		return "xdm/code/codeEdit";
	}
	// 코드 추가
	@RequestMapping("/xdm/system/code/create")
	public String createCode(CodeDto dto) {
		service.createCode(dto);
		return "redirect:/xdm/system/code/list";
	}
	// 코드 수정
	@RequestMapping("/xdm/system/code/update")
	public String updateCode(CodeDto dto) {
		service.updateCode(dto);
		return "redirect:/xdm/system/code/list";
	}
	// 코드 softDelete
	@RequestMapping("/xdm/system/code/softDelete")
	public String softDeleteCode(@RequestParam("delSeq") List<String> idList) {
		for(String id : idList) {
			if(!id.equals("")) {
				service.softDeleteCode(id);
			}
		}
		return "redirect:/xdm/system/code/list";
	}
	// 코드 hardDelete
	@RequestMapping("/xdm/system/code/hardDelete")
	public String hardDeleteCode(@RequestParam("delSeq") List<String> idList) {
		for(String id : idList) {
			if(!id.equals("")) {
				service.hardDeleteCode(id);
			}
		}
		return "redirect:/xdm/system/code/list";
	}
	// Excel 다운로드
	@GetMapping("/xdm/system/code/excel")
	public void downloadCodeExcel(HttpServletResponse response, PageVo pageVo, SearchVo searchVo) throws IOException {
	    List<CodeDto> codes = service.findCodesByVo(pageVo, searchVo); // 필터링 적용된 목록

	    // 엑셀 워크북 생성
	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("Codes");

	    // 헤더
	    Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue("사용 여부");
	    header.createCell(1).setCellValue("코드그룹 번호");
	    header.createCell(2).setCellValue("코드그룹명");
	    header.createCell(3).setCellValue("코드 번호");
	    header.createCell(4).setCellValue("코드명");
	    header.createCell(5).setCellValue("등록일");
	    header.createCell(6).setCellValue("수정일");

	    // 내용
	    int rowNum = 1;
	    for (CodeDto code : codes) {
	        Row row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue(code.getIsUsed() == 1 ? "Y" : "N");
	        row.createCell(1).setCellValue(code.getCodegroupId());
	        row.createCell(2).setCellValue(code.getCodegroupName());
	        row.createCell(3).setCellValue(code.getCodeId());
	        row.createCell(4).setCellValue(code.getName());
	        row.createCell(5).setCellValue(code.getCreatedAt());
	        row.createCell(6).setCellValue(code.getUpdatedAt());
	    }
	    
	    // 응답 설정
	    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	    response.setHeader("Content-Disposition", "attachment; filename=codes.xlsx");

	    // 엑셀 파일 내보내기
	    workbook.write(response.getOutputStream());
	    workbook.close();
	}
}