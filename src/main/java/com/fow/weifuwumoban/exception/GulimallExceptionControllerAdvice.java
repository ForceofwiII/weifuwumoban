package com.fow.weifuwumoban.exception;


import cn.dev33.satoken.exception.NotLoginException;
import com.alibaba.fastjson.JSON;
import com.fow.weifuwumoban.enums.BizCodeEnume;
import com.fow.weifuwumoban.utils.R;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 集中处理所有异常
 */
@Slf4j
//@ResponseBody
//@ControllerAdvice(basePackages = "com.atguigu.gulimall.product.controller")
@RestControllerAdvice()
public class GulimallExceptionControllerAdvice {



    /**
     * 处理 @Valid 校验失败异常
     */

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e){
        log.error("数据校验出现问题{}，异常类型：{}",e.getMessage(),e.getClass());
        String err = "";
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
            err += error.getDefaultMessage()+" ";
        }

        // 收集对象级别的校验错误
        for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
            errors.put(error.getObjectName(), error.getDefaultMessage());
           err+=error.getDefaultMessage()+" ";
        }



        return R.error(BizCodeEnume.VAILD_EXCEPTION.getCode(),err);
    }


    /**
     * 处理 @Validated 校验参数失败异常（针对单个参数校验）
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R handleConstraintViolationException(ConstraintViolationException ex) {
        // 收集所有违反约束的信息
        Map<String, String> errors = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));

        String error = JSON.toJSONString(errors);
        return R.error(BizCodeEnume.VAILD_EXCEPTION.getCode(),error);
    }


    /**
     * 处理 satoken 令牌过期异常
     */

    @ExceptionHandler(value = NotLoginException.class)
    public  R notLoginException(NotLoginException e){
        log.error("错误："+e.getMessage());

        return R.error(BizCodeEnume.UNKNOW_EXCEPTION.getCode(),e.getType());

    }



    @ExceptionHandler(value = RuntimeException.class)
    public R handleRuntimeException(RuntimeException e){
        log.error("错误："+e.getMessage());
        return R.error(BizCodeEnume.UNKNOW_EXCEPTION.getCode(),e.getMessage());
    }





    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){

        log.error("错误：",throwable);
        return R.error(BizCodeEnume.UNKNOW_EXCEPTION.getCode(),BizCodeEnume.UNKNOW_EXCEPTION.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public R handleAnyException(Exception e){
        log.error("错误：",e);
        return R.error(BizCodeEnume.UNKNOW_EXCEPTION.getCode(),BizCodeEnume.UNKNOW_EXCEPTION.getMsg());
    }


}
