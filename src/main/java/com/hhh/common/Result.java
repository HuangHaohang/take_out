package com.hhh.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Result
 * @Description TODO
 * @Author HHH
 * @Date 2022/8/6 0:13
 * @Version 1.0
 **/
@Data
public class Result<T> {
    /**
     * 错误信息
     */
    private String msg;

    /**
     * 状态码，1为成功，0为失败
     */
    private Integer code;

    /**
     * 数据
     */
    private Object data;

    /**
     * 动态数据
     */
    private final Map<String, Object> map = new HashMap<>();

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.code = 1;
        result.data = object;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.code = 0;
        result.msg = msg;
        return result;
    }

    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public Object get(String key) {
        return this.map.get(key);
    }
}
