package com.myboot.validation.validators.jobValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Nansen
 * @create 2020/4/15 12:42
 */
public class JobValidator implements ConstraintValidator<Job, com.myboot.vo.Job> {
    @Override
    public void initialize(Job constraintAnnotation) {

    }

    @Override
    public boolean isValid(com.myboot.vo.Job job, ConstraintValidatorContext constraintValidatorContext) {
        Integer id = job.getId();
        if(id == 11){
            return true;
        }
        return false;
    }
}
