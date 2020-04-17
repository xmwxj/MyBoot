package com.myboot.vo;

import com.myboot.validation.groups.*;
import com.myboot.validation.validators.marriage.Marriage2;
import com.myboot.validation.regexp.ValidationRegexp;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * @author Nansen
 * @create 2020/4/4 21:28
 */
@Marriage2(sex = "sex", age = "age")
public class User {
    @NotNull(groups={Update.class, Delete.class})
    private Integer id;
    @NotBlank(groups={Update.class,Create.class})
    private String name;
    @Max(value = 50,groups = {Update.class})
    @Max(30)
    private int age;
    @Pattern(regexp = ValidationRegexp.NUMNIC,message = "sex输入不合法")
    private String sex;
    @com.myboot.validation.validators.jobValidator.Job(groups = {Step1.class})
    @Valid
    private Job job1;

    @com.myboot.validation.validators.jobValidator.Job
    @Valid
    private Job job2;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Job getJob1() {
        return job1;
    }

    public void setJob1(Job job1) {
        this.job1 = job1;
    }

    public Job getJob2() {
        return job2;
    }

    public void setJob2(Job job2) {
        this.job2 = job2;
    }
}
