package com.myboot.validation.validators.marriage;

/**
 * @author Nansen
 * @create 2020/4/17 13:27
 */

import javax.validation.Payload;

/**
 * 类级别验证2
 */
public @interface Marriage2 {
    String sex() default "sex";
    String age() default "age";

    String message() default "不符合国家法定结婚年龄";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
