package com.hhh.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisPlusConfig
 * @Description MP配置类
 * @Author HHH
 * @Date 2022/8/6 20:20
 * @Version 1.0
 **/
@Configuration
public class MybatisPlusConfig {

    /**
      * @Author          HuangHH
      * @Description     //MP分页插件
      * @Date            20:22 2022/8/6
      * @Param           []
      * @return          com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
      **/
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(
                new PaginationInnerInterceptor()
        );
        return mybatisPlusInterceptor;
    }
}
