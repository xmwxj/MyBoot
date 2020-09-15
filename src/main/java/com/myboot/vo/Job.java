package com.myboot.vo;

import com.myboot.validation.regexp.ValidationRegexp;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Nansen
 * @create 2020/4/5 13:13
 */
public class Job {
    @NotNull
    @Max(8)
    @Pattern(regexp= ValidationRegexp.NUMERIC)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
