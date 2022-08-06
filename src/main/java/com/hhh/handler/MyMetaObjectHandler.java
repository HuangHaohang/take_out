package com.hhh.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hhh.utils.BaseContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName MyMetaObjectHandler
 * @Description 自定义元数据对象处理器，自动填充公共字段
 * @Author HHH
 * @Date 2022/8/6 22:07
 * @Version 1.0
 **/
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
      * @Author          HuangHH
      * @Description     //当方法为插入操作时，自动填充
      * @Date            22:09 2022/8/6
      * @Param           [metaObject]
      * @return          void
      **/
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("createUser", BaseContextUtils.getCurrentId());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContextUtils.getCurrentId());
    }

    /**
     * @Author          HuangHH
     * @Description     //当方法为更新操作时，自动填充
     * @Date            22:09 2022/8/6
     * @Param           [metaObject]
     * @return          void
     **/
    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContextUtils.getCurrentId());
    }
}
