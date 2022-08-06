package com.hhh.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhh.common.Result;
import com.hhh.entity.Category;

/**
 * @ClassName CategoryService
 * @Description 分类管理的Service接口
 * @Author HHH
 * @Date 2022/8/6 22:36
 * @Version 1.0
 **/
public interface CategoryService extends IService<Category> {
    /**
      * @Author          HuangHH
      * @Description     //新增分类功能接口
      * @Date            22:46 2022/8/6
      * @Param           [category]
      * @return          void
      **/
    void saveCategory(Category category);

    /**
      * @Author          HuangHH
      * @Description     //分页查询功能接口
      * @Date            22:57 2022/8/6
      * @Param           [page, pageSize]
      * @return          void
      **/
    Result<Page<Category>> queryPage(int page, int pageSize);

    /**
      * @Author          HuangHH
      * @Description     //删除分类功能接口
      * @Date            23:28 2022/8/6
      * @Param           [id]
      * @return          com.hhh.common.Result<java.lang.String>
      **/
    Result<String> removeCategory(Long id);

    /**
      * @Author          HuangHH
      * @Description     //更新分类信息接口
      * @Date            0:05 2022/8/7
      * @Param           [category]
      * @return          com.hhh.common.Result<java.lang.String>
      **/
    Result<String> updateCategory(Category category);
}
