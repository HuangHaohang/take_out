package com.hhh.config;


import com.hhh.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


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
}
