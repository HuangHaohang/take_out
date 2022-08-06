package com.hhh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhh.entity.Category;
import com.hhh.mapper.CategoryMapper;
import com.hhh.service.CategoryService;
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
}
