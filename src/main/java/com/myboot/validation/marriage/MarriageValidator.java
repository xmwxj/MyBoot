package com.myboot.validation.marriage;

import com.myboot.vo.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Nansen
 * @create 2020/4/15 12:42
 */
public class MarriageValidator implements ConstraintValidator<Marriage, User> {
    @Override
    public void initialize(Marriage constraintAnnotation) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        //Male or Female
        String sex = user.getSex();
        int age = user.getAge();
        if("Male".equals(sex)){
            return age>24;
        }else if("Female".equals(sex)){
            return age >22;
        }
        return false;
    }
}
