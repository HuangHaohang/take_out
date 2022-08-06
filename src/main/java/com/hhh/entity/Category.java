package com.hhh.entity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
  * @Author          HuangHH
  * @Description     //分类管理实体类
  * @Date            22:32 2022/8/6
  **/
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    /**类型 1 菜品分类 2 套餐分类*/
    private Integer type;


    /**分类名称*/
    private String name;


    /**顺序*/
    private Integer sort;


    /**创建时间*/
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    /**更新时间*/
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    /**创建人*/
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    /**修改人*/
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


    /**是否删除*/
    private Integer isDeleted;

}
