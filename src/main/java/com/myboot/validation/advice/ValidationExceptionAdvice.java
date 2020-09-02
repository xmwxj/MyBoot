//package com.myboot.validation.advice;
//
//import com.myboot.vo.BaseResponse;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author Nansen
// * @create 2020/4/5 12:10
// */
//@RestControllerAdvice
//public class ValidationExceptionAdvice {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public final BaseResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        BaseResponse response = new BaseResponse();
////        List<FieldError> errorList =  ex.getBindingResult().getFieldErrors();
//        List<ObjectError> errorList  = ex.getBindingResult().getAllErrors();
//        List<String> msgList = errorList.stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
//        response.setErrorMessage(msgList);
//        return  response;
//    }
//}
