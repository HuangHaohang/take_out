package com.hhh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhh.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName EmployeeMapper
 * @Description 员工操作Mapper接口
 * @Author HHH
 * @Date 2022/8/5 21:57
 * @Version 1.0
 **/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
