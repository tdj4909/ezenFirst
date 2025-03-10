package com.a2a2lab.module.codeGroup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeGroupController {

	@RequestMapping(value = "/codeGroup")
	public String codeGroup() {
		return "/xdm/codeGroup/codeGroupXdmList";
	}
}
