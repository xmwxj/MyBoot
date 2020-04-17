package com.myboot.validation.jobValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Nansen
 * @create 2020/4/15 12:41
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = JobValidator.class)
public @interface Job {
    String message() default "Job有问题";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
