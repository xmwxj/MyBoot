package com.myboot.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Nansen
 * @create 2020/9/2 16:08
 */
public enum Gender {
    MALE(1), FEMALE(2);
    private int value;

    Gender(int value){
        this.value=value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @JsonCreator
    public static Gender valueOf(int value) {
        for(Gender gender:Gender.values()){
            if(gender.getValue() == value){
                return gender;
            }
        }
        return null;
    }
}
