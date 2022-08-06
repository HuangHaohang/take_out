package com.hhh.exception;

/**
 * @ClassName CustomException
 * @Description 自定义业务异常处理类
 * @Author HHH
 * @Date 2022/8/6 23:51
 * @Version 1.0
 **/

public class CustomException extends RuntimeException{
    public CustomException(String msg) {
        super(msg);
    }
}
