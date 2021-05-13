package com.obtk.config.Interceptors;

import com.obtk.handler.HandlerBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorsConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor = registry.addInterceptor(new HandlerBean());
        interceptor.addPathPatterns("/**");
        interceptor.excludePathPatterns("/showImage.do",
                "/login.do",
                "/checkCode",
                "/upload.do",
                "/findByIDUser.do",
                "/test01.do");

    }
}
