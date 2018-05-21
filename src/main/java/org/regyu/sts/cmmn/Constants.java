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
	
	final static String ERROR_SAMECHAR = "erros.sameChar";
	final static String ERROR_CONINUOUSCHAR = "erros.coninuousChar";
	final static String ERROR_OVERLAPCHAR = "errors.overlapChar";
	final static String ERROR_Qwerty = "errors.qwerty";
	final static String QWERT_STRING = "1234567890-=!@#$%^&*()_+qwertyuiop[]QWERTYUIOP{}asdfghjkl;'ASDFGHJKL:zxcvbnm,./ZXCVBNM<>?";
	
	final static String FAIL 		="fail";
	final static String SUCCESS 		="success";
	
	
	final static String EMAIL_REGEX		= "^[_0-9a-zA-Z-\\.]+@[_0-9a-zA-Z-]+\\.([\\._0-9a-zA-Z-]+)*$";
	final static String PHONENUMBER_REGEX	= "^(01[016789]{1}|02|03[12]{1}|04[1234]{1}|05[12345]{1}|06[1234]{1})-[0-9]{3,4}-[0-9]{4}$";
	//final static String PHONENUMBER_REGEX	= "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
	final static String ERROR_Regex		= "^[_0-9a-zA-Z-\\.]+@[_0-9a-zA-Z-]+\\.([\\._0-9a-zA-Z-]+)*$";
	
	
}
