package com.a2a2lab.module.codeGroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.a2a2lab.module.code.CodeService;
import com.a2a2lab.module.code.CodeVo;

@Controller
public class CodeGroupController {

    private final CodeService codeService;

	@Autowired
	CodeGroupService codeGroupService;

    CodeGroupController(CodeService codeService) {
        this.codeService = codeService;
    }
	
	@RequestMapping(value = "/codeGroupXdmList")
	public String codeGroupXdmList(@ModelAttribute("vo") CodeGroupVo vo, Model model) throws Exception {
		
//		setSearch(vo);
		vo.setParamsPaging(codeGroupService.selectOneCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("lists", codeGroupService.selectList(vo));
		}
		
		
		return "/xdm/codeGroup/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/codeGroupXdmInst")
	public String codeGroupXdmInst(CodeGroupDto codeGroupDto) {
		codeGroupService.insert(codeGroupDto);
		return "redirect:/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/codeGroupXdmUpdt")
	public String codeGroupXdmUpdt(CodeGroupDto codeGroupDto) {
		codeGroupService.update(codeGroupDto);
		return "redirect:/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/codeGroupXdmDele")
	public String codeGroupXdmDele(CodeGroupDto codeGroupDto) {
		codeGroupService.delete(codeGroupDto);
		return "redirect:/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/codeGroupXdmUele")
	public String codeGroupXdmUele(@RequestParam("delSeq") List<String> delSeq) {

		for(String delseq : delSeq) {
			if(!delseq.equals("")) {
				CodeGroupDto codeGroupDto = new CodeGroupDto();
				codeGroupDto.setIfcgSeq(delseq);
				codeGroupService.uelete(codeGroupDto);
			}
		}
		return "redirect:/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/usrIndex")
	public String usrIndex() {
		return "/usr/index/index";
	}
	
	@RequestMapping(value = "/codeGroupXdmRegister")
	public String codeGroupXdmRegister(@ModelAttribute("vo") CodeGroupVo vo, Model model) throws Exception{
		
		if (vo.getIfcgSeq().equals("0") || vo.getIfcgSeq().equals("")) {
//			insert mode
		} else {
//			update mode
			model.addAttribute("item", codeGroupService.selectOne(vo));
		}
		return "/xdm/codeGroup/codeGroupXdmRegister";
	}
	
}