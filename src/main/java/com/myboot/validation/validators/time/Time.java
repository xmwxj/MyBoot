package com.myboot.validation.validators.time;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeValidator.class)
public @interface Time {
	String message() default "wrong format of time";
	String pattern() default "";
	
	 Class<?>[] groups() default {};
	 Class<? extends Payload>[] payload() default {};
}
