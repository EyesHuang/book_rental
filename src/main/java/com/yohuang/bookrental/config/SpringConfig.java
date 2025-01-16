package com.yohuang.bookrental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
public class SpringConfig {
    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer pageableCustomizer() {
        return pageableResolver -> {
            pageableResolver.setPageParameterName("offset");
            pageableResolver.setSizeParameterName("limit");
            pageableResolver.setOneIndexedParameters(false);
        };
    }
}
