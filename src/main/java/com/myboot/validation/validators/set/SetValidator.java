package com.myboot.validation.validators.set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SetValidator implements ConstraintValidator<Set, Object>{
	private String stringSet;
	@Override
	public void initialize(Set set) {
		this.stringSet = set.value();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(null==stringSet || "".equals(stringSet)) {
			return false;
		}
		//not to validation null value
		if(null == value || "".equals(value)) {
			return true;
		}
		if(value instanceof String){

		}
		String[] patterns = stringSet.split(",");
		for(String pattern: patterns) {
			if(pattern.equals(value)) {
				return true;
			}
		}
		return false;
	}
}