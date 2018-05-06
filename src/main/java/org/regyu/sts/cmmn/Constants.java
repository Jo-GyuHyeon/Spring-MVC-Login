package org.regyu.sts.cmmn;

/**
 * @author 유재영
 * @Description 프로젝트에서 사용하는 상수등르 관리하는 인터페이스
 */
public interface Constants {
	final static String ERROR_REQUIRED	= "errors.required";
	final static String ERROR_EMAIL		= "errors.email";
	final static String ERROR_MINLENGTH	= "errors.minLength";
	final static String ERROR_MAXLENGTH	= "errors.maxLength";
	final static String ERROR_PWCONFIRM	= "errors.pwConfirm";
	final static String ERROR_PHONENUMBER= "errors.phoneNumber";
	
	final static String EMAIL_REGEX		= "^[_0-9a-zA-Z-\\.]+@[_0-9a-zA-Z-]+\\.([\\._0-9a-zA-Z-]+)*$";
	final static String PHONENUMBER_REGEX	= "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
	
	
}
