package org.regyu.sts.cmmn.validator;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import org.regyu.sts.cmmn.Constants;
import org.regyu.sts.singin.service.SingInVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("singInVOValidator")
public class SingInVOValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(SingInVOValidator.class);

	// context-message.xml message Bean DI
	@Resource(name = "messageSource")
	private MessageSource message;

	/*
	 * @Value("${NotEmpty}") private String jaeyoung;
	 */

	// 1차검문 (타입검사)
	@Override
	public boolean supports(Class<?> clazz) {
		logger.info("supports(Class<?> clazz)");

		return SingInVO.class.equals(clazz); // supports 는 해당 검사대상이 오브젝트인지 아닌지 검사함 (오브젝트 여야함)
	}

	// 2차검문
	@Override
	public void validate(Object target, Errors errors) {
		logger.info("validate(Object target, Errors errors)");

		SingInVO singInVO = (SingInVO) target;

		/*
		 * if ("".equals(singInVO.getMberId()) || singInVO.getMberId() == null) {
		 * errors.rejectValue("mberId", "errors.required", new Object[]
		 * {"'아이디 혹은 이메일'","'테스트1","'테스트2'"}, "아이디는 필수입력값"); } //rejectValue(1.=
		 * field,(jsp path일치) / 2.= errorCode,(context-message.xml 프로퍼티)/ 3.
		 * errorArgs(context-message.xml 프로퍼티의 파라미터) ex).{0} / 4. defaultMessage (메세지
		 * 우선순위낮음)
		 */

		System.out.println("벨리데이터 테스트");

		mberIdValidChk("singInVO.mberId", singInVO.getMberId(), errors);
		passwordValidChk("singInVO.password", singInVO.getPassword(), singInVO, errors);
		passwordConfirmValidChk("singInVO.passwordConfirm", singInVO.getPasswordConfirm(), singInVO, errors);
		mberNmValidChk("singInVO.mberNm", singInVO.getMberNm(), errors);
		phoneNumberValidChk("singInVO.phoneNumber", singInVO.getPhoneNumber(), errors);
	}

	private String getPropMessage(String string) {
		return message.getMessage(string, null, Locale.KOREA);
	}

	private void mberIdValidChk(String string, String mberId, Errors errors) {
		boolean chk = false;

		String fieldName = "mberId", errorCode = Constants.ERROR_REQUIRED, propFieldName = getPropMessage(string);

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
		String regex = Constants.EMAIL_REGEX;

		if (!mberId.matches(regex)) {
			return false;
		}

		return true;
	}

	private void passwordValidChk(String string, String password, SingInVO singInVO, Errors errors) {
		boolean chk = false;

		String fieldName = "password", errorCode = Constants.ERROR_REQUIRED, propFieldName = getPropMessage(string);

		chk = nullOrEmptyChk(password);

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName }, null);
			return;
		}

		// 최소 글자 유효성 체크 시작
		int minLengthCnt = 6;
		
		errorCode = Constants.ERROR_MINLENGTH;
		chk = minLengthChk(password, minLengthCnt);
		
		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName, minLengthCnt }, null);

			return;
		}
		
		// 최 글자 유효성 체크 시작
		int maxLengthCnt = 16;
		
		errorCode = Constants.ERROR_MAXLENGTH;
		chk = maxLengthChk(password, maxLengthCnt);
		
		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName, maxLengthCnt }, null);

			return;
		}
		
		// 비밀번호 네자리 연속 불가능 유효성 체크 시작
		errorCode = Constants.ERROR_SAMECHAR;

		int sameCharCnt = 4;
		chk = sameCharChk(password, sameCharCnt);

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName, sameCharCnt }, null);

			return;
		}

		// 비밀번호 연속된 ㅣ문자 여부 유효성 체크 시작
		errorCode = Constants.ERROR_CONINUOUSCHAR;

		int ContinuousCharCnt = 4;
		chk = continuousCharChk(password, ContinuousCharCnt);

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName, ContinuousCharCnt }, null);
			return;
		}

		// 비밀번호 입력시 아이디와 겹치지 않게 유효성 체크 끝
		errorCode = Constants.ERROR_OVERLAPCHAR;
		
		int overlapCharcnt = 6;
		chk = overlapCharChk(password, singInVO.getMberId(), overlapCharcnt);

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName, overlapCharcnt }, null);

			return;
		}

		// 비밀번호 qwerty와 같이 입력시 유효성 체크 시작
		errorCode = Constants.ERROR_Qwerty;
		
		int qwertyCharChkCnt = 4;
		chk = qwertyCharChk(password, qwertyCharChkCnt);

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName, overlapCharcnt }, null);

			return;
		}
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
	
	private boolean sameCharChk(String password, int sameCharCnt) {
		int index = 0;

		char checkPassword, checkPassword2;

		for (int i = 0; i < password.length() - 1; i++) {
			checkPassword = password.charAt(i);
			checkPassword2 = password.charAt(i + 1);

			if (checkPassword == checkPassword2) {
				index++;
			} else {
				index = 0;
			}

			if (index >= sameCharCnt - 1) {
				return false;
			}
		}

		return true;
	}
	
	private boolean continuousCharChk(String password, int continuousCharCnt) {
		int totCnt1 = 0, totCnt2 = 0, cnt1 = 0, cnt2 = 0;

		for (int i = 0; i < password.length() - 1; i++) {
			cnt1 = Character.codePointAt(password, i);
			cnt2 = Character.codePointAt(password, i + 1);

			totCnt1 = (cnt1 - cnt2 == 1) ? totCnt1 + 1 : 0;
			totCnt2 = (cnt2 - cnt1 == 1) ? totCnt2 + 1 : 0;

			if (totCnt1 >= continuousCharCnt - 1 || totCnt2 >= continuousCharCnt - 1) {
				return false;
			}
		}

		return true;
	}
	
	private boolean overlapCharChk(String password, String mberId, int overlapCharcnt) {
		String group = password.substring(0, overlapCharcnt);

		StringBuilder build = new StringBuilder();
		build.append("(" + group + ")");

		int endIdx = 0, pwLength = password.length();

		for (int i = 0; i <= pwLength - overlapCharcnt; i++) {
			endIdx = (i + overlapCharcnt);

			group = password.substring(i, endIdx);
			build.append("|(" + group + ")");
		}

		Pattern pattern = Pattern.compile(build.toString());
		Matcher matcher = pattern.matcher(mberId);

		if (matcher.find()) {
			boolean findChk = ((matcher.end() - matcher.start()) == overlapCharcnt);

			if (findChk) {
				return false;
			}
		}

		return true;
	}
	
	private boolean qwertyCharChk(String password, int qwertyCharChkCnt) {
		String qwertyChar = Constants.QWERT_STRING;
		
		String group = password.substring(0, qwertyCharChkCnt);

		StringBuilder build = new StringBuilder();
		build.append("(" + group + ")");

		int endIdx = 0, pwLength = password.length();

		for (int i = 0; i <= pwLength - qwertyCharChkCnt; i++) {
			endIdx = (i + qwertyCharChkCnt);

			group = password.substring(i, endIdx);
			build.append("|(" + group + ")");
		}

		Pattern pattern = Pattern.compile(build.toString());
		Matcher matcher = pattern.matcher(qwertyChar);

		if (matcher.find()) {
			boolean findChk = ((matcher.end() - matcher.start()) == qwertyCharChkCnt);

			if (findChk) {
				return false;
			}
		}

		return true;
	}

	private void passwordConfirmValidChk(String string, String passwordConfirm, SingInVO singInVO, Errors errors) {
		boolean chk = false;

		String fieldName = "passwordConfirm", errorCode = Constants.ERROR_REQUIRED,
				propFieldName = getPropMessage(string);

		chk = nullOrEmptyChk(passwordConfirm);

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName }, null);
		}

		// 비밀번호 일치여부 유효성 체크 시작
		errorCode = Constants.ERROR_PWCONFIRM;

		chk = pwConfirm(passwordConfirm, singInVO.getPassword());

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

	private void mberNmValidChk(String string, String mberNm, Errors errors) {
		boolean chk = false;

		String fieldName = "mberNm", errorCode = Constants.ERROR_REQUIRED, propFieldName = getPropMessage(string);

		chk = nullOrEmptyChk(mberNm);

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName }, null);
		}
	}

	private void phoneNumberValidChk(String string, String phoneNumber, Errors errors) {
		boolean chk = false;

		String fieldName = "phoneNumber", errorCode = Constants.ERROR_REQUIRED, propFieldName = getPropMessage(string);

		chk = nullOrEmptyChk(phoneNumber);

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName }, null);

			return;
		}
		// 전화번호 유효성 체크 시작
		errorCode = Constants.ERROR_PHONENUMBER;

		chk = phoneNumberChk(phoneNumber);

		if (!chk) {
			errors.rejectValue(fieldName, errorCode, new Object[] { propFieldName }, null);

			return;
		}
	}

	private boolean phoneNumberChk(String phoneNumber) {
		String regex = Constants.PHONENUMBER_REGEX;

		if (!phoneNumber.matches(regex)) {
			return false;
		}

		return true;
	}

	private boolean nullOrEmptyChk(String fieldValue) {

		if ("".equals(fieldValue) || fieldValue == null) {
			return false;
		}

		return true;
	}
}
