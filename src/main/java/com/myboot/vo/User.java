package com.myboot.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myboot.validation.groups.Create;
import com.myboot.validation.groups.Delete;
import com.myboot.validation.groups.Step1;
import com.myboot.validation.groups.Update;
import com.myboot.validation.regexp.ValidationRegexp;
import com.myboot.validation.validators.marriage.Marriage2;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

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
    @Min(value=10,groups = {Create.class})
    @Max(value = 8,groups = {Update.class})
    private int age;
    private Gender gender;
    @Min(1)
    @Max(31)
    private Integer flag;
    @com.myboot.validation.validators.jobValidator.Job(groups = {Step1.class})
    @Valid
    private Job job1;
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date birtDate;

    @com.myboot.validation.validators.jobValidator.Job
    @Valid
    private Job job2;

    public User() {
    }

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

    public Integer getFlag(){
        return  flag;
    }

    public void setFlag(Integer flag){
        this.flag = flag;
    }

    public void setBirtDate(Date date){
        this.birtDate = date;
    }

    public Date getBirtDate(){
        return this.birtDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
