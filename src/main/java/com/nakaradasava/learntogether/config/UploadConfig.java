package com.nakaradasava.learntogether.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Allow spring boot to load static directories without restart application.
 * Configuration added because image upload.
 */
@Configuration
public class UploadConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:C:\\Users\\Korisnik\\IdeaProjects\\Learn Together\\src\\main\\resources\\static\\images\\");
    }
}
