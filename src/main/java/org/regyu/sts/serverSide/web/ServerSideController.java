package org.regyu.sts.serverSide.web;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.regyu.sts.cmmn.validator.ServerSideVOValidator;
import org.regyu.sts.serverSide.service.AnnoServerSideVO;
import org.regyu.sts.serverSide.service.ServerSideVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServerSideController {

	private static final Logger logger = LoggerFactory.getLogger(ServerSideController.class);
	
	//custom validator
	@Resource(name = "serverSideVOValidator")
	private ServerSideVOValidator validator;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		logger.info("인잇바인더");
		
		dataBinder.setValidator(validator);
	}

	@RequestMapping(value = "serverSide.do")
	public String serverSide(@ModelAttribute ServerSideVO serverSideVO) throws Exception {
		logger.info("serverSide.do");
		return "serverSide/serverSide";
	}
	@RequestMapping(value = "serverSideValidChk.do")
	public String serverSideValidChk(@ModelAttribute @Validated ServerSideVO serverSideVO,
			BindingResult result, ModelMap model) throws Exception {
		logger.info("serverSideValidChk");
		
//		@Validated 가 없을때 객체를 이용하여 check custom VO validation 
//		 validator.validate(serverSideVO, result);
//		@Validated 있을 때 @InitBinder 와 함께 사용
		
		 //result custom VO validation
		if (result.hasErrors()) {
			model.addAttribute("eSangMu", "N");
		} else {
			model.addAttribute("eSangMu", "Y");
		}
		
		return "serverSide/serverSide";
	}
	
	//annotaion VO vaild check
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
	
//	@Valid = JSR-303의 빈검증기를 이용하여 모델 오브젝트를 검증하는 지시자
	
//	@InitBinder = 
//	1. InitBinder(WebdataBinder) 를 사용하여 사용하는경우 @ModelAttribute를 사용하지 않아도 @Valid만 사용하여 값들이 자동 binding되는 결과물들을 얻을 수 있다.
//	WebDataBinder는 web request parameter를 JavaBean 객체에 바인딩하는 특정한 DataBinder이다. WebDataBinder는 웹 환경이 필요하지만, Servlet API에 의존적이지 않다. Servlet API에 의존적인 ServletRequestDataBinder와 같이 특정한 DataBinder를 위한 더 많은 base classs를 제공한다. 
//	주의사항 : BindingResult가 ModelMap 뒤로 가지 않도록 한다. (리스판스 되는 내용의..???/)

//	2. Spring Web 환경에서는 기존 Servlet을 이용했을 때의 상황과는 달리, Java의 reflection 기법을 이용하여 parameter 들을 POJO 기반의 Class로 setter 주입을 하여 제공을 하게 된다.
//	 기본 자료형 변수들이 setter 될때 Type이 일치 하지 않는 경우, 예외가 발생하게 된다. 이런 상황을 방지하기 위하여 initBinder라는 애노테이션을 이용하게 된다.

}