package com.myboot.vo;

import java.util.List;

/**
 * @author Nansen
 * @create 2020/4/5 12:12
 */
public class BaseResponse {
    public List<String> errorMessage;

    public List<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<String> errorMessage) {
        this.errorMessage = errorMessage;
    }
}
