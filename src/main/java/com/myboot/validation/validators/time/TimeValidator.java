package com.myboot.validation.validators.time;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimeValidator implements ConstraintValidator<Time, String> {
	private String  pattern;
	
	@Override
	public void initialize(Time time) {
		this.pattern = time.pattern();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(StringUtils.isEmpty(value)){
			return true;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			simpleDateFormat.parse(value);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
}