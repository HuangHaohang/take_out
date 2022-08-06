package com.hhh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhh.exception.CustomException;
import com.hhh.common.Result;
import com.hhh.entity.Category;
import com.hhh.entity.Dish;
import com.hhh.entity.Setmeal;
import com.hhh.mapper.CategoryMapper;
import com.hhh.service.CategoryService;
import com.hhh.service.DishService;
import com.hhh.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CategoryServiceImpl
 * @Description 分类管理接口的实现类
 * @Author HHH
 * @Date 2022/8/6 22:37
 * @Version 1.0
 **/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private DishService dishService;

    /**
      * @Author          HuangHH
      * @Description     //新增分类功能实现方法
      * @Date            22:46 2022/8/6
      * @Param           [category]
      * @return          void
      **/
    @Override
    public void saveCategory(Category category) {
        save(category);
    }

    /**
      * @Author          HuangHH
      * @Description     //分页查询功能实现方法
      * @Date            22:58 2022/8/6
      * @Param           [page, pageSize]
      * @return          void
      **/
    @Override
    public Result<Page<Category>> queryPage(int page, int pageSize) {
        //  分页构造器
        Page<Category> pageInfo = new Page<>(page, pageSize);
        //  条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //  添加排序条件，根据sort进行正序排序
        queryWrapper.orderByAsc(Category::getSort);
        //  进行分页查询
        page(pageInfo, queryWrapper);
        return Result.success(pageInfo);
    }

    /**
      * @Author          HuangHH
      * @Description     //根据id删除分类，删除之前需要进行判断此分类是否关联菜品或套餐
      * @Date            23:29 2022/8/6
      * @Param           [id]
      * @return          com.hhh.common.Result<java.lang.String>
      **/
    @Override
    public Result<String> removeCategory(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //  添加查询条件，根据分类id进行查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int countDish = dishService.count(dishLambdaQueryWrapper);
        //  查询当前分类是否关联了菜品，如果已经关联，抛出一个业务异常
        if (countDish > 0) {
            //  已经关联菜品，抛出一个业务异常
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //  添加查询条件，根据分类id进行查询
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int countSetMeal = setmealService.count(setmealLambdaQueryWrapper);
        //  查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常
        if (countSetMeal > 0) {
            //  已经关联套餐，抛出一个业务异常
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }
        //  正常删除
        super.removeById(id);
        return Result.success("分类信息删除成功");
    }

    /**
      * @Author          HuangHH
      * @Description     //根据id修改分类信息
      * @Date            0:06 2022/8/7
      * @Param           [category]
      * @return          com.hhh.common.Result<java.lang.String>
      **/
    @Override
    public Result<String> updateCategory(Category category) {
        updateById(category);
        return Result.success("修改分类信息成功");
    }

}
