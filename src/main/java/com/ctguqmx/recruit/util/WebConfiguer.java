package com.ctguqmx.recruit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

   registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");


    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
////         addPathPatterns("/**") 表示拦截所有的请求，
////         excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/user/login","/error","/user/dologin","/front/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/user/**","/student/**").excludePathPatterns("/checkTel","/checkstudentId","/grade","/signup","/grade","/dograde","/","/success","/user/login","/error","/user/dologin","/static/**","/css/**","/js/**","/images/**","/scss/**","/webfonts/**","/vendor/**");

    }
}
