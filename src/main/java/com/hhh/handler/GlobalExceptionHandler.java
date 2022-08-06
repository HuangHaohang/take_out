package com.hhh.handler;

import com.hhh.common.Result;
import com.hhh.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理器
 * @Author HHH
 * @Date 2022/8/6 19:57
 * @Version 1.0
 **/
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
      * @Author          HuangHH
      * @Description     //处理SQLIntegrityConstraintViolationException异常
      * @Date            20:03 2022/8/6
      * @Param           []
      * @return          com.hhh.common.Result<java.lang.String>
      **/
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.error(ex.getMessage());
        if (ex.getMessage().contains("Duplicate entry")){
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return Result.error(msg);
        }
        return Result.error("未知错误");
    }

    /**
      * @Author          HuangHH
      * @Description     //处理自定义业务异常
      * @Date            23:54 2022/8/6
      * @Param           [ex]
      * @return          com.hhh.common.Result<java.lang.String>
      **/
    @ExceptionHandler(CustomException.class)
    public Result<String> exceptionHandler(CustomException ex) {
        return Result.error(ex.getMessage());
    }
}
