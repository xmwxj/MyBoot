package com.myboot.vo;

/**
 * @author Nansen
 * @create 2020/9/2 16:08
 */
public enum Gender implements BaseEnum {
    MALE(0),FEMAIL(1);
    private int value;

    Gender(int value){
        this.value=value;
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public BaseEnum valueOf() {
        for(Gender gender:Gender.values()){
            if(gender.getValue() == value){
                return gender;
            }
        }
        return null;
    }
}
