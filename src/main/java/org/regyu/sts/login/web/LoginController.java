package org.regyu.sts.login.web;

import java.util.HashMap;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.regyu.sts.cmmn.Constants;
import org.regyu.sts.cmmn.JsonUtil;
import org.regyu.sts.login.service.EgovUserDetailsHelper;
import org.regyu.sts.login.service.LoginService;
import org.regyu.sts.login.service.LoginVO;
import org.regyu.sts.singin.service.SingInVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;
	
	@Resource(name = "loginService")
	private LoginService loginService;

	@RequestMapping(value = "login.do")
	public String login(@ModelAttribute SingInVO singInVO) throws Exception {
		logger.info("login.do");

		return "login/login";
	}

	@RequestMapping(value = "actionLogin.do")
	public String actionLogin(@ModelAttribute LoginVO loginVO, ModelMap model, HttpServletRequest request)
			throws Exception {
		logger.info("actionLogin.do");
		
		LoginVO resultVO = loginService.actionLogin(loginVO);
		
		if (resultVO == null) {

			model.addAttribute("message", messageSource.getMessage("fail.common.msg", null, Locale.KOREA));

			return "login/login";
		} else {
			request.getSession().setAttribute("loginVO", resultVO);

			return "redirect:/actionMain.do";
		}
	}
	
	@RequestMapping(value = "actionMain.do")
	public String actionMain(ModelMap model, HttpServletRequest request) throws Exception {
		logger.info("actionMain.do");
		
		LoginVO user	= (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		if (user == null) {
			model.addAttribute("message", "로그인에 실패하였습니다.");
			
			request.getSession().setAttribute("loginVO", null);
			
			return "login/login";
		} else {
			
			model.addAttribute("user", user);
			
			return "main/main.tiles";
		}
	}
	
	@RequestMapping(value = "resetPwd.do", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String resetPwd(@RequestParam HashMap<String, String> reqMap) throws Exception {
		logger.info("resetPwd.do");
		
		String result = null;
		try {
			result = loginService.resetPassword(reqMap);
			
			if(Constants.SUCCESS.equals(result)) {
				loginService.sendEmail(reqMap);
			}

		} catch (Exception e) {

		}
		return result;
	}
	
	@RequestMapping(value = "resetPasswordForm.do")
	public String resetPasswordForm(@RequestParam HashMap<String, String> reqMap, ModelMap model) throws Exception {
		logger.info("resetPasswordForm.do");
		
		boolean result = loginService.getResetUser(reqMap);
		
		if(result) {
			model.addAttribute("reqMap",reqMap);
			
			return "login/resetPasswordForm";
		}else {
			return "redirect:main.do";
		}

	}
	
	@RequestMapping(value = "resetPasswordProc.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String resetPasswordProc(@RequestParam HashMap<String, String> reqMap) throws Exception {
		logger.info("resetPasswordProc.do");
//		String result = loginService.resetPasswordProc(reqMap);
		String result = loginService.resetPasswordProcTx(reqMap);
		
		if(Constants.SUCCESS.equals(result)) {
			return JsonUtil.OneStringToJson(Constants.SUCCESS);
		}else {
			return JsonUtil.OneStringToJson(Constants.FAIL);
		}
		
	}
	
	@RequestMapping(value = "logout.do")
	public String logout(HttpServletRequest request) throws Exception {
		logger.info("logout.do");
		
		request.getSession().setAttribute("loginVO", null);
		
		return "main/main.tiles";
	}

}