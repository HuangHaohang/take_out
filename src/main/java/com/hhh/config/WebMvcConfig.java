package com.hhh.config;


import com.hhh.common.JacksonObjectMapper;
import com.hhh.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;


/**
 * @ClassName WebMvcConfig
 * @Description WebMvc配置类
 * @Author HHH
 * @Date 2022/8/5 21:48
 * @Version 1.0
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport{
    /**
      * @Author          HuangHH
      * @Description     //指定静态资源不被拦截
      *                    extends WebMvcConfigurationSupport，继承的方式会导致静态资源拦截
      *                    如果改为实现接口的方式就不会被拦截 implements WebMvcConfigurer
      * @Date            14:19 2022/8/6
      * @Param           registry
      * @return          void
      **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    /**
      * @Author          HuangHH
      * @Description     //拦截除了排除掉的页面外的一切请求，进入到LoginInterceptor中进行登录判断
      * @Date            16:34 2022/8/6
      * @Param           [registry]
      * @return          void
      **/
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                //  排除以下路径进入的请求，不进行拦截
                .excludePathPatterns(
                        "/employee/login",
                        "/employee/logout",
                        "/backend/**",
                        "/front/**"
                );
    }

    /**
      * @Author          HuangHH
      * @Description     //扩展mvc框架的消息转换器
      * @Date            21:29 2022/8/6
      * @Param           [converters]
      * @return          void
      **/
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //  创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //  设置对象转换器，底层使用Jackson将Java对象转为JSON
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //  将上面的消息转换器对象追加到mvc框架的转换器容器集合中
        converters.add(0,messageConverter);
    }
}
