package com.hhh.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhh.common.Result;
import com.hhh.mybatis.entity.Employee;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName EmployeeService
 * @Description 员工业务逻辑接口
 * @Author HHH
 * @Date 2022/8/5 21:54
 * @Version 1.0
 **/
public interface EmployeeService extends IService<Employee> {
    /**
      * @Author          HuangHH
      * @Description     //员工登录功能接口
      * @Date            0:20 2022/8/6
      * @Param           [request, employee]
      * @return          com.hhh.common.Result<com.hhh.mybatis.entity.Employee>
      **/
    Result<Employee> login(HttpServletRequest request, Employee employee);

    /**
      * @Author          HuangHH
      * @Description     //员工退出功能接口
      * @Date            0:35 2022/8/6
      * @Param           [request]
      * @return          com.hhh.common.Result<java.lang.String>
      **/
    Result<String> logout(HttpServletRequest request);

    /**
      * @Author          HuangHH
      * @Description     //新增员工功能接口
      * @Date            16:55 2022/8/6
      * @Param           [employee]
      * @return          com.hhh.common.Result<java.lang.String>
      **/
    Result<String> insert(HttpServletRequest request,Employee employee);

    /**
      * @Author          HuangHH
      * @Description     //分页查询功能接口
      * @Date            20:40 2022/8/6
      * @Param           [page, pageSize, name]
      * @return          com.hhh.common.Result<com.baomidou.mybatisplus.extension.plugins.pagination.Page>
      **/
    Result<Page<Employee>> queryPage(int page, int pageSize, String name);

    /**
      * @Author          HuangHH
      * @Description     //修改员工信息方法
      * @Date            21:00 2022/8/6
      * @Param           [employee]
      * @return          com.hhh.common.Result<java.lang.String>
      **/
    Result<String> updateEmployeeInfo(HttpServletRequest request, Employee employee);
}
