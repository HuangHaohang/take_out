package com.hhh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhh.common.Result;
import com.hhh.mapper.EmployeeMapper;
import com.hhh.mybatis.entity.Employee;
import com.hhh.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

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

    /**
      * @Author          HuangHH
      * @Description     //新增员工功能方法
      * @Date            16:55 2022/8/6
      * @Param           [employee]
      * @return          com.hhh.common.Result<java.lang.String>
      **/
    @Override
    public Result<String> insert(HttpServletRequest request,Employee employee) {
        //  1.设置初始密码，比进行md5加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //  2.设置该员工账号创建时间
        employee.setCreateTime(LocalDateTime.now());
        //  3.设置当前这条数据的更新时间
        employee.setUpdateTime(LocalDateTime.now());
        //  4.获得当前登录用户的ID
        Long empId = (Long) request.getSession().getAttribute("emp");
        //  5.创建用户
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        save(employee);
        return Result.success("新增员工成功");
    }

    /**
      * @Author          HuangHH
      * @Description     //分页查询方法实现
      * @Date            20:40 2022/8/6
      * @Param           [page, pageSize, name]
      * @return          com.hhh.common.Result<com.baomidou.mybatisplus.extension.plugins.pagination.Page>
      **/
    @Override
    public Result<Page<Employee>> queryPage(int page, int pageSize, String name) {
        //  1.构造分页构造器
        Page<Employee> pageInfo = new Page<>(page, pageSize);
        //  2.构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //  2.1添加过滤条件
        queryWrapper.like(org.apache.commons.lang.StringUtils.isNotEmpty(name), Employee::getUsername, name);
        //  2.2添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //  3.执行查询
        page(pageInfo, queryWrapper);
        return Result.success(pageInfo);
    }
}
