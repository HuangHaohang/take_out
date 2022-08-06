package com.hhh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhh.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName CategoryMapper
 * @Description 分类管理的Mapper接口
 * @Author HHH
 * @Date 2022/8/6 22:34
 * @Version 1.0
 **/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
