package com.crack.snap.make.spring.ai.chatbot.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Mohan Sharma
 */
@Configuration
public class CorsWebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
       //todo add cors mapping
        registry.addMapping("/inference")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("POST")
                .allowedHeaders("Content-Type");
    }

}
