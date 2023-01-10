package com.example.restStudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

//         registry.addViewController("/admin").setViewName("listBS2");
          registry.addViewController("/admin").setViewName("index");
          registry.addViewController("/user").setViewName("user");
    }



}
