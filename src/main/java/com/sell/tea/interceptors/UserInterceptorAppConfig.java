package com.sell.tea.interceptors;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class UserInterceptorAppConfig implements WebMvcConfigurer {
    private final UserInterceptor userInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/api/product/")
                .addPathPatterns("/api/auth/current-user")
                .addPathPatterns("/api/cart")
                .addPathPatterns("/api/order");
    }
}
