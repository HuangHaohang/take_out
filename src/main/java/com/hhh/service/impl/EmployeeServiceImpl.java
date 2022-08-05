package com.hhh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhh.common.Result;
import com.hhh.mapper.EmployeeMapper;
import com.hhh.mybatis.entity.Employee;
import com.hhh.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName EmployeeServiceImpl
 * @Description 员工业务逻辑方法实现类
 * @Author HHH
 * @Date 2022/8/5 21:57
 * @Version 1.0
 **/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    /**
      * @Author          HuangHH
      * @Description     //员工登录功能实现
      * @Date            0:21 2022/8/6
      * @Param           [request, employee]
      * @return          com.hhh.common.Result<com.hhh.mybatis.entity.Employee>
      **/
    @Override
    public Result<Employee> login(HttpServletRequest request, Employee employee) {
        //  1.获取前端输入的密码， 并进行md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //  2.查询数据库用户名是否存在
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = getOne(wrapper);
        if (StringUtils.isEmpty(emp)) {
            //  2.1如果不存在，则返回【该用户不存在，请先注册】信息
            return Result.error("该用户不存在，请先注册");
        }
        //  2.2若存在，则进行密码比对
        if (!emp.getPassword().equals(password)) {
            //  2.2.1如果比对错误，则返回【密码错误，请重新输入】信息
            return Result.error("密码错误，请重新输入");
        }
        //  2.3若密码比对正确，则进行员工状态是否禁用查询
        if (emp.getStatus() == 0) {
            //  2.3.1若被禁用，则返回【该员工已被禁用，请联系管理员】信息
            return Result.error("该员工已被禁用，请联系管理员");
        }
        //  3.若前面都成功，则将查询到的对象返回，并将对象ID存放的Session中
        request.getSession().setAttribute("emp",emp.getId());
        return Result.success(emp);
    }

    /**
      * @Author          HuangHH
      * @Description     //员工退出功能实现
      * @Date            0:35 2022/8/6
      * @Param           [request]
      * @return          com.hhh.common.Result<java.lang.String>
      **/
    @Override
    public Result<String> logout(HttpServletRequest request) {
        //  1.清楚浏览器保存的session信息
        request.getSession().removeAttribute("emp");
        //  2.返回退出成功
        return Result.success("退出成功");
    }
}
