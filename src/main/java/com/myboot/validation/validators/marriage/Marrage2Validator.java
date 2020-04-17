package com.myboot.validation.validators.marriage;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Nansen
 * @create 2020/4/17 13:29
 */
public class Marrage2Validator implements ConstraintValidator<Marriage2, Object> {
    private String sex;
    private String age;

    @Override
    public void initialize(Marriage2 marriage2) {
        this.sex = marriage2.sex();
        this.age = marriage2.age();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(null == o) {
            return true;
        }
        BeanWrapper beanWrapper = new BeanWrapperImpl(o);
        String sexValue = (String) beanWrapper.getPropertyValue(this.sex);
        int ageValue = (int) beanWrapper.getPropertyValue(this.age);

        if("Male".equals(sexValue)){
            return ageValue>24;
        }else if("Female".equals(sexValue)){
            return ageValue >22;
        }

        return false;
    }
}
