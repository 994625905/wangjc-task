package com.wangjc.task.config;

import com.wangjc.task.filter.WangjcTaskFilter;
import com.wangjc.task.interceptors.WangjcTaskInterceptor;
import com.wangjc.task.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web层核心配置
 * @author com.wangjc
 * @title: WebConfig
 * @projectName wangjc-blog
 * @description: TODO
 * @date 2020/6/1711:44
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RedisService redisService;
    /**
     * 认证中心
     */
    @Value("${wangjc.task.autologin}")
    private String loginURL;
    /**
     * 重定向的登录地址
     */
    @Value("${wangjc.task.redirect-server}")
    private String redirectServer;
    /**
     * 默认页
     */
    @Value("${wangjc.task.mainPage}")
    private String mainPage;

    /**
     * 静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/",
                "classpath:/resources/", "classpath:/static/", "classpath:/resource/", "classpath:/", "classpath*:*");
    }

    /**
     * 默认打开页面
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName(mainPage);
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WangjcTaskInterceptor()).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * 注册过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean registrationFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new WangjcTaskFilter(redisService,loginURL,redirectServer));
        registration.addUrlPatterns("/*");//添加过滤规则
        registration.setName("ReportFilter");
        registration.setOrder(1);//优先级
        return registration;
    }

}
