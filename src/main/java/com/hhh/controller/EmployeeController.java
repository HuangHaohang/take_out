package com.hhh.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhh.common.Result;
import com.hhh.mybatis.entity.Employee;
import com.hhh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        return employeeService.login(request, employee);
    }

    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request) {
        return employeeService.logout(request);
    }

    @PostMapping
    public Result<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        return employeeService.insert(request, employee);
    }

    @GetMapping("/page")
    public Result<Page<Employee>> page(int page, int pageSize, String name) {
        return employeeService.queryPage(page, pageSize, name);
    }

    @PutMapping
    public Result<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        return employeeService.updateEmployeeInfo(request, employee);
    }

    /**
      * @Author          HuangHH
      * @Description     //根据ID查询员工信息，显示在编辑的页面
      * @Date            21:41 2022/8/6
      * @Param           [id]
      * @return          com.hhh.common.Result<com.hhh.mybatis.entity.Employee>
      **/
    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return Result.success(employee);
        }
        return Result.error("没有查询到对应的员工信息");
    }
}
