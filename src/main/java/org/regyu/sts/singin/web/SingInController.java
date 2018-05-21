package org.regyu.sts.singin.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.regyu.sts.cmmn.validator.SingInVOValidator;
import org.regyu.sts.singin.service.AnnoSingInVO;
import org.regyu.sts.singin.service.SingInService;
import org.regyu.sts.singin.service.SingInVO;
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
public class SingInController {

	private static final Logger logger = LoggerFactory.getLogger(SingInController.class);
	
	//custom validator
	@Resource(name = "singInVOValidator")
	private SingInVOValidator validator;
	
	@Resource(name = "singInService")
	private SingInService singInService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		logger.info("인잇바인더");
		
		dataBinder.setValidator(validator);
	}

	@RequestMapping(value = "singIn.do")
	public String singIn(@ModelAttribute SingInVO singInVO) throws Exception {
		logger.info("singIn.do");
		return "singIn/singIn";
	}
	@RequestMapping(value = "singInValidChk.do")
	public String singInValidChk(@ModelAttribute @Validated SingInVO singInVO,
			BindingResult result, ModelMap model) throws Exception {
		logger.info("singInValidChk");
		
//		@Validated 가 없을때 객체를 이용하여 check custom VO validation 
//		 validator.validate(singInVO, result);
//		@Validated 있을 때 @InitBinder 와 함께 사용
		
		 //result custom VO validation
		if (result.hasErrors()) {
			model.addAttribute("eSangMu", "N");
		} else {
			model.addAttribute("eSangMu", "Y");
		}
		
		return "singIn/singIn";
	}
	
	@RequestMapping(value = "singInInsert.do")
	public String serverSideInsert(@ModelAttribute SingInVO singInVO) throws Exception {
		logger.info("singInInsert.do");
		
		try {
			singInService.insertMber(singInVO);		
		} catch (Exception e) {
			e.getStackTrace();
		}
<<<<<<< HEAD
		
		return "singIn/singIn";
=======
		return "forward:/login.do";
		//return "singIn/singIn";
>>>>>>> origin/dec_valid
	}
	
	//annotaion VO vaild check
	@RequestMapping(value = "annosingIn.do")
	public String annosingIn(@ModelAttribute AnnoSingInVO annosingInVO) throws Exception {
		logger.info("annosingIn.do");
<<<<<<< HEAD
=======
		
>>>>>>> origin/dec_valid
		return "singIn/singIn";
	}
	
	@RequestMapping(value = "annosingInValidChk.do")
	public String annosingInValidChk(@ModelAttribute @Valid AnnoSingInVO annosingInVO,
			BindingResult bindingResult) throws Exception {
		logger.info("annosingInValidChk.do");
		logger.info("에러 여부 : " + bindingResult.hasErrors());

		return "singIn/singIn";
	}
	
//	@Valid = JSR-303의 빈검증기를 이용하여 모델 오브젝트를 검증하는 지시자
	
//	@InitBinder = 
//	1. InitBinder(WebdataBinder) 를 사용하여 사용하는경우 @ModelAttribute를 사용하지 않아도 @Valid만 사용하여 값들이 자동 binding되는 결과물들을 얻을 수 있다.
//	WebDataBinder는 web request parameter를 JavaBean 객체에 바인딩하는 특정한 DataBinder이다. WebDataBinder는 웹 환경이 필요하지만, Servlet API에 의존적이지 않다. Servlet API에 의존적인 ServletRequestDataBinder와 같이 특정한 DataBinder를 위한 더 많은 base classs를 제공한다. 
//	주의사항 : BindingResult가 ModelMap 뒤로 가지 않도록 한다. (리스판스 되는 내용의..???/)

//	2. Spring Web 환경에서는 기존 Servlet을 이용했을 때의 상황과는 달리, Java의 reflection 기법을 이용하여 parameter 들을 POJO 기반의 Class로 setter 주입을 하여 제공을 하게 된다.
//	 기본 자료형 변수들이 setter 될때 Type이 일치 하지 않는 경우, 예외가 발생하게 된다. 이런 상황을 방지하기 위하여 initBinder라는 애노테이션을 이용하게 된다.

}