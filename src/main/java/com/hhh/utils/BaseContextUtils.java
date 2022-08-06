package com.hhh.utils;

/**
 * @ClassName BaseContextUtils
 * @Description 基于ThreadLocal封装的工具类，用于保存和获取当前登录用户id
 *              其作用范围是在某一个线程之内，因为线程是隔离的，因此修改次线程的变量不会影响其他线程
 * @Author HHH
 * @Date 2022/8/6 22:22
 * @Version 1.0
 **/
public class BaseContextUtils {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
      * @Author          HuangHH
      * @Description     //设置当前线程的id值
      * @Date            22:29 2022/8/6
      * @Param           [id]
      * @return          void
      **/
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
      * @Author          HuangHH
      * @Description     //获取当前线程的id值
      * @Date            22:29 2022/8/6
      * @Param           []
      * @return          java.lang.Long
      **/
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
