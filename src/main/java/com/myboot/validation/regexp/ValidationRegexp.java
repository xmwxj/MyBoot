package com.myboot.validation.regexp;

/**
 * @author Nansen
 * @create 2020/4/10 13:26
 */
public interface ValidationRegexp {
    //匹配数字或者空字符串
    public String NUMERIC = "^\\s*|[1-9]\\d*$|0";
    public static final String NUMERIC2 = "^\\s*|[1-9]\\d*$|0|^$";
}
