package com.kagouniv.kagouniv_back.config;

import com.kagouniv.kagouniv_back.auth.HttpUserIdArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebMVCConfig implements WebMvcConfigurer {
    private final HttpUserIdArgumentResolver httpUserIdArgumentResolver;

    @Override
    public void addCorsMappings(final CorsRegistry registry ){
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("PATCH","GET","POST","PUT","DELETE","HEAD","OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(this.httpUserIdArgumentResolver);
    }
}
