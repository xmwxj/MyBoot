package com.myboot.validation.validators.set;

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
@Constraint(validatedBy = SetValidator.class)
public @interface Set {
	String message() default "value no specified";
	String value() default "";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
