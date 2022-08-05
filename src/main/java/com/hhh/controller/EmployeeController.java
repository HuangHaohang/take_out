package com.hhh.controller;

import com.hhh.common.Result;
import com.hhh.mybatis.entity.Employee;
import com.hhh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName EmployeeController
 * @Description 员工Controller层
 * @Author HHH
 * @Date 2022/8/5 21:52
 * @Version 1.0
 **/
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        return employeeService.login(request, employee);
    }

    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request){
        return employeeService.logout(request);
    }
}
