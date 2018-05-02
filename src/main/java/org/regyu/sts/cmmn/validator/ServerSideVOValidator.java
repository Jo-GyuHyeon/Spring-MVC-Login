package org.regyu.sts.cmmn.validator;

import java.util.Locale;
import javax.annotation.Resource;
import org.regyu.sts.cmmn.Constants;
import org.regyu.sts.serverSide.service.ServerSideVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("serverSideVOValidator")
public class ServerSideVOValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(ServerSideVOValidator.class);

	// context-message.xml message Bean DI
	@Resource(name = "messageSource")
	private MessageSource message;

	/*
	 * @Value("${NotEmpty}") 
	 * private String jaeyoung;
	 */

	// 1차검문 (타입검사)
	@Override
	public boolean supports(Class<?> clazz) {
		logger.info("supports(Class<?> clazz)");

		return ServerSideVO.class.equals(clazz); // supports 는 해당 검사대상이 오브젝트인지 아닌지 검사함 (오브젝트 여야함)
	}

	// 2차검문
	@Override
	public void validate(Object target, Errors errors) {
		logger.info("validate(Object target, Errors errors)");

		ServerSideVO serverSideVO = (ServerSideVO) target;
		
		/*
		if ("".equals(serverSideVO.getMberId()) || serverSideVO.getMberId() == null) {
			errors.rejectValue("mberId", "errors.required", new Object[] {"'아이디 혹은 이메일'","'테스트1","'테스트2'"}, "아이디는 필수입력값");
		}
		//rejectValue(1.= field,(jsp path일치) / 2.= errorCode,(context-message.xml 프로퍼티)/ 3. errorArgs(context-message.xml 프로퍼티의 파라미터) ex).{0} / 4. defaultMessage (메세지 우선순위낮음)
		*/

		System.out.println("벨리데이터 테스트");

		mberIdValidChk("serverSideVO.mberId", serverSideVO.getMberId(), serverSideVO, errors);
		passwordValidChk("serverSideVO.password", serverSideVO.getPasswordConfirm(), serverSideVO, errors);
		passwordConfirmValidChk("serverSideVO.passwordConfirm", serverSideVO.getPasswordConfirm(), serverSideVO, errors);
		mberNmValidChk("serverSideVO.mberNm", serverSideVO.getMberNm(), serverSideVO, errors);
		phoneNumberValidChk("serverSideVO.phoneNumber", serverSideVO.getPhoneNumber(), serverSideVO, errors);
	}
	
	private String getPropMessage(String string) {
		return message.getMessage(string, null, Locale.KOREA);
	}
	
	private void mberIdValidChk(String string, String mberId, ServerSideVO serverSideVO, Errors errors) {
		boolean chk = false;

		String fieldName = "mberId", 
				errorCode = Constants.ERROR_REQUIRED, 
				propFieldName = getPropMessage(string);

		chk = nullOrEmptyChk(mberId);

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName }, null);
			return;
		}

		// 이메일 유효성 체크 시작
		errorCode = Constants.ERROR_EMAIL;

		chk = emailChk(mberId);

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName }, null);

			return;
		}
	}

	private boolean emailChk(String mberId) {
		String regex = Constants.ERROR_REGEX;

		if (!mberId.matches(regex)) {
			return false;
		}

		return true;
	}
	
	private void passwordValidChk(String string, String password, ServerSideVO serverSideVO, Errors errors) {
		boolean chk = false;

		String fieldName = "password", 
				errorCode = Constants.ERROR_REQUIRED,
				propFieldName = getPropMessage(string);

		chk = nullOrEmptyChk(password);

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName }, null);
			return;
		}

		// 최소 글자 유효성 체크 시작
		int minLengthCnt = 6;
		int maxLengthCnt = 16;
		int cnt = 0; // 구분자

		if (password.length() < 6) {
			errorCode = Constants.ERROR_MINLENGTH;
			chk = minLengthChk(password, minLengthCnt);
			cnt = minLengthCnt;
		} else if (password.length() > 16) {
			errorCode = Constants.ERROR_MAXLENGTH;
			chk = maxLengthChk(password, maxLengthCnt);
			cnt = maxLengthCnt;
		}

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName, cnt }, null);

			return;
		}
	}

	private void passwordConfirmValidChk(String string, String passwordConfirm, ServerSideVO serverSideVO,
			Errors errors) {
		boolean chk = false;

		String fieldName = "passwordConfirm", 
				errorCode = Constants.ERROR_REQUIRED,
				propFieldName = getPropMessage(string);

		chk = nullOrEmptyChk(passwordConfirm);

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName }, null);
		}

		// 비밀번호 일치여부 유효성 체크 시작
		errorCode = Constants.ERROR_PWCONFIRM;

		chk = pwConfirm(passwordConfirm, serverSideVO.getPassword());

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName }, null);

			return;
		}
	}

	private boolean pwConfirm(String passwordConfirm, String password) {

		if (!passwordConfirm.equals(password)) {
			return false;
		}

		return true;
	}

	private boolean maxLengthChk(String password, int maxLengthCnt) {

		if (password.length() > maxLengthCnt) {
			return false;
		}

		return true;
	}

	private boolean minLengthChk(String password, int minLengthCnt) {

		if (password.length() < minLengthCnt) {
			return false;
		}

		return true;
	}
	
	private void mberNmValidChk(String string, String mberNm, ServerSideVO serverSideVO, Errors errors) {
		boolean chk = false;

		String fieldName = "mberNm", 
				errorCode = Constants.ERROR_REQUIRED, 
				propFieldName = getPropMessage(string);

		chk = nullOrEmptyChk(mberNm);

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName }, null);
		}
	}
	
	private void phoneNumberValidChk(String string, String phoneNumber, ServerSideVO serverSideVO, Errors errors) {
		boolean chk = false;

		String fieldName = "phoneNumber", 
				errorCode = Constants.ERROR_REQUIRED,
				propFieldName = getPropMessage(string);

		chk = nullOrEmptyChk(phoneNumber);

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName }, null);
		}
	}


	private boolean nullOrEmptyChk(String fieldValue) {

		if ("".equals(fieldValue) || fieldValue == null) {
			return false;
		}

		return true;
	}
}
