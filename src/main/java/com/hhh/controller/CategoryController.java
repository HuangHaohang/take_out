package com.hhh.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhh.common.Result;
import com.hhh.entity.Category;
import com.hhh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CategoryController
 * @Description TODO
 * @Author HHH
 * @Date 2022/8/6 22:39
 * @Version 1.0
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result<String> saveCategory(@RequestBody Category category) {
        /*
         *  因为已经添加了全局异常处理器，所以不用对是否新增成功进行判断
         *  如果添加失败，会自动进行异常处理
         *  if (categoryService.saveDishes(category)) {
         *      return Result.success("新增菜品分类成功");
         *  }
         *  return Result.error("新增菜品分类失败");
         */
        categoryService.saveCategory(category);
        return Result.success("新增菜品分类成功");
    }

    @GetMapping("/page")
    public Result<Page<Category>> page(int page, int pageSize){
        return categoryService.queryPage(page, pageSize);
    }

    @DeleteMapping
    public Result<String> deleteCategory(Long ids){
        return categoryService.removeCategory(ids);
    }

    @PutMapping
    public Result<String> updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }
}
