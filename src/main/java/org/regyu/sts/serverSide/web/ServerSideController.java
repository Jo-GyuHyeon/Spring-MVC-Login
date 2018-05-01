package org.regyu.sts.serverSide.web;

import javax.validation.Valid;

import org.regyu.sts.serverSide.service.AnnoServerSideVO;
import org.regyu.sts.serverSide.service.ServerSideVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServerSideController {

	private static final Logger logger = LoggerFactory.getLogger(ServerSideController.class);

	@RequestMapping(value = "serverSide.do")
	public String serverSide(@ModelAttribute ServerSideVO serverSideVO) throws Exception {
		logger.info("serverSide.do");
		return "serverSide/serverSide";
	}
	@RequestMapping(value = "serverSideValidChk.do")
	public String serverSideValidChk(@ModelAttribute @Validated ServerSideVO serverSideVO,
			BindingResult result,
			ModelMap model) throws Exception {
		logger.info("serverSideValidChk");
		
		// validator.validate(serverSideVO, result);
		
		if (result.hasErrors()) {
			model.addAttribute("eSangMu", "N");
		} else {
			model.addAttribute("eSangMu", "Y");
		}
		
		return "serverSide/serverSide";
	}
	
	@RequestMapping(value = "annoServerSide.do")
	public String annoServerSide(@ModelAttribute AnnoServerSideVO annoServerSideVO) throws Exception {
		return "serverSide/serverSide";
	}
	
	@RequestMapping(value = "annoServerSideValidChk.do")
	public String annoServerSideValidChk(@ModelAttribute @Valid AnnoServerSideVO annoServerSideVO,
			BindingResult bindingResult) throws Exception {
		logger.info("에러 여부 : " + bindingResult.hasErrors());

		return "serverSide/serverSide";
	}

}