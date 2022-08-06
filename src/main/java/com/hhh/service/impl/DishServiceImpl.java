package com.hhh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhh.entity.Dish;
import com.hhh.mapper.DishMapper;
import com.hhh.service.DishService;
import org.springframework.stereotype.Service;

/**
 * @ClassName DishServiceImpl
 * @Description 菜品业务逻辑功能实现类
 * @Author HHH
 * @Date 2022/8/6 23:38
 * @Version 1.0
 **/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
