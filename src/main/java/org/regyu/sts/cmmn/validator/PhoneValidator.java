package org.regyu.sts.cmmn.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	@Override
	public void initialize(Phone arg0) {

	}

	@Override
	public boolean isValid(String phoneNo, ConstraintValidatorContext ctx) {

		if (phoneNo == null) {
			return false;
		}

		return phoneNo.matches("^[0-9]*$");
		// ^문자열 시작
		// [] 문자의 집합이나 범위 - 의 사이값
		// 앞 문자가 없을 수도 무한정 많을 수도 있음
		// $문자열의 종료
	}

}
