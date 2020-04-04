package com.myboot.controllers.user;

import javax.validation.constraints.NotBlank;

/**
 * @author Nansen
 * @create 2020/4/4 21:28
 */
public class User {
    @NotBlank
    private String name;
    @NotBlank
    private String age;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
