package org.regyu.sts.login.web;

import org.regyu.sts.singin.service.SingInVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "login.do")
	public String login(@ModelAttribute SingInVO singInVO) throws Exception {
		logger.info("회원가입 : " + singInVO.getMberId());
		
		return "login/login";
	}
	
	
}