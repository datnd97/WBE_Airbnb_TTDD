package com.security.demospringsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class uploadConfig implements WebMvcConfigurer {
    @Autowired
    Environment ev;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        String uploadPath = ev.getProperty("uploadPath");
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + uploadPath);
    }

}
