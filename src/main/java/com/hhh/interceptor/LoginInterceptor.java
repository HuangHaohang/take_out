package com.hhh.interceptor;

import com.alibaba.fastjson.JSON;
import com.hhh.common.Result;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginInterceptor
 * @Description 登录拦截器，未登录则被拦截
 * @Author HHH
 * @Date 2022/8/6 16:26
 * @Version 1.0
 **/
public class LoginInterceptor implements HandlerInterceptor{

    /**
      * @Author          HuangHH
      * @Description     //当未登录时，则会被拦截不放行
      * @Date            16:36 2022/8/6
      * @Param           [request, response, handler]
      * @return          boolean
      **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("emp") != null) {
            return true;
        } else {
            response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));
            return false;
        }
    }
}
