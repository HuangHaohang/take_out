package com.hhh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhh.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName DishMapper
 * @Description 菜品Mapper
 * @Author HHH
 * @Date 2022/8/6 23:35
 * @Version 1.0
 **/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
