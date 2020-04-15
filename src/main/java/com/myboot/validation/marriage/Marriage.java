package com.myboot.validation.marriage;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Nansen
 * @create 2020/4/15 12:41
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = MarriageValidator.class)
public @interface Marriage {
    String message() default "不符合国家法定结婚年龄";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
