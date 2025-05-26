package com.a2a2lab.module.codeGroup;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.a2a2lab.module.vo.PageVo;
import com.a2a2lab.module.vo.SearchVo;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CodeGroupController {

	@Autowired
	CodeGroupService service;
	
	// 코드그룹 관리 화면
	@RequestMapping("/xdm/system/codegroup/list")
	public String showCodeGroupManagement(Model model, PageVo pageVo, SearchVo searchVo) {
		// 검색 설정
		model.addAttribute("searchVo", searchVo);
		// 페이징 설정
		pageVo.setParamsPaging(service.countCodeGroupsByVo(searchVo));
		model.addAttribute("pageVo", pageVo);
		// 코드그룹 출력
		model.addAttribute("list", service.findCodeGroupsByVo(pageVo, searchVo));
		return "xdm/codegroup/codeGroupList";
	}
	
	// 코드그룹 등록/수정 화면
	@RequestMapping("/xdm/system/codegroup/edit")
	public String showCodeGroupEdit(Model model, @RequestParam("codegroupId") String codegroupId){
		// codegroupId가 있으면 수정, 없으면 등록
		if (!codegroupId.equals("") && !codegroupId.equals("0")) {
			model.addAttribute("item", service.findCodeGroupById(codegroupId));
		}
		return "xdm/codegroup/codeGroupEdit";
	}
	
	// 코드그룹 추가
	@RequestMapping("/xdm/system/codegroup/create")
	public String createCodeGroup(CodeGroupDto dto) {
		service.createCodeGroup(dto);
		return "redirect:/xdm/system/codegroup/list";
	}
	
	// 코드그룹 수정
	@RequestMapping("/xdm/system/codegroup/update")
	public String updateCodeGroup(CodeGroupDto dto) {
		service.updateCodeGroup(dto);
		return "redirect:/xdm/system/codegroup/list";
	}
	
	// 코드그룹 softDelete
	@RequestMapping("/xdm/system/codegroup/softDelete")
	public String softDeleteCodeGroup(@RequestParam("delSeq") List<String> idList) {
		for(String id : idList) {
			if(!id.equals("")) {
				service.softDeleteCodeGroup(id);
			}
		}
		return "redirect:/xdm/system/codegroup/list";
	}
	
	// 코드그룹 hardDelete
	@RequestMapping("/xdm/system/codegroup/hardDelete")
	public String hardDeleteCodeGroup(@RequestParam("delSeq") List<String> idList) {
		for(String id : idList) {
			if(!id.equals("")) {
				service.hardDeleteCodeGroup(id);
			}
		}
		return "redirect:/xdm/system/codegroup/list";
	}
	
	// Excel 다운로드
	@GetMapping("/xdm/system/codegroup/excel/download")
	public void downloadCodeGroupExcel(HttpServletResponse response, PageVo pageVo, SearchVo searchVo) throws IOException {
		pageVo.setParamsPaging(service.countCodeGroupsByVo(searchVo));
		List<CodeGroupDto> codeGroups = service.findCodeGroupsByVo(pageVo, searchVo); // 필터링 적용된 목록

	    // 엑셀 워크북 생성
	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("CodeGroups");

	    // 헤더
	    Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue("사용 여부");
	    header.createCell(1).setCellValue("코드그룹 번호");
	    header.createCell(2).setCellValue("코드그룹명");
	    header.createCell(3).setCellValue("개수");
	    header.createCell(4).setCellValue("등록일");
	    header.createCell(5).setCellValue("수정일");

	    // 내용
	    int rowNum = 1;
	    for (CodeGroupDto group : codeGroups) {
	        Row row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue(group.getIsUsed() == 1 ? "Y" : "N");
	        row.createCell(1).setCellValue(Integer.parseInt(group.getCodegroupId()));
	        row.createCell(2).setCellValue(group.getName());
	        row.createCell(3).setCellValue(group.getCodeCount());
	        row.createCell(4).setCellValue(group.getCreatedAt());
	        row.createCell(5).setCellValue(group.getUpdatedAt());
	    }
	    
	    // 열 너비 자동 조절
	    for (int i = 0; i < 6; i++) {
	        sheet.autoSizeColumn(i);
	        sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1024); // 약간 여유 (한글 대응)
	    }

	    // 응답 설정
	    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	    response.setHeader("Content-Disposition", "attachment; filename=codegroups.xlsx");

	    // 엑셀 파일 내보내기
	    workbook.write(response.getOutputStream());
	    workbook.close();
	}
	// Excel 업로드
	@RequestMapping("/xdm/system/codegroup/excel/upload")
	public String uploadExcel(@RequestParam("excelFile") MultipartFile file) throws IOException {
	    InputStream inputStream = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        List<CodeGroupDto> codeList = new ArrayList<>();

        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) continue;

            CodeGroupDto code = new CodeGroupDto();

            code.setIsUsed(row.getCell(0).getStringCellValue().equals("Y") ? 1 : 0);
            code.setName(row.getCell(2).getStringCellValue());

            codeList.add(code);
        }

        workbook.close();

        // 저장
        for(CodeGroupDto dto : codeList) {
        	service.createCodeGroup(dto);
        }

        return "redirect:/xdm/system/codegroup/list"; // 업로드 후 목록 페이지로 리다이렉트
	}
		
}