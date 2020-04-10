package com.myboot.vo;

import com.myboot.validation.groups.Create;
import com.myboot.validation.groups.Delete;
import com.myboot.validation.groups.Update;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * @author Nansen
 * @create 2020/4/4 21:28
 */
public class User {
    @NotNull(groups={Update.class, Delete.class})
    private Integer id;
    @NotBlank(groups={Update.class,Create.class})
    private String name;
    @Max(value = 50,groups = {Update.class})
    @Max(30)
    private int age;
    private String sex;
    @NotNull
    @Valid
    private Job job;

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

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
