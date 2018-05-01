package org.regyu.sts.cmmn.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
   String message() default "전화번호 형식이 맞지 않습니다.";
   
   Class<?>[] groups() default {};
   
   Class<? extends Payload>[] payload() default {};
}

//@Documented 문서에도 어노테이션의 정보가 표현됩니다.
//@Constraint(validatedBy = PhoneValidator.class) 제약 조건을 나타내는 에노테이션 입니다. 구현한 
//@Target({ElementType.METHOD, ElementType.FIELD}) 어노테이션이 적용할 위치를 결정합니다.
//@Retention(RetentionPolicy.RUNTIME) 어노테이션의 범위 || 어떤 시점까지 어노테이션이 영향을 미치는지 결정합니다.