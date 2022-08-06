package com.hhh.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
  * @Author          HuangHH
  * @Description     //员工实体类
  * @Date            21:59 2022/8/5
  **/
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;

    private Integer status;

    /**插入时，填充字段*/
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**插入和更新时，填充字段*/
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**插入时，填充字段*/
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**插入和更新时，填充字段*/
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

}
