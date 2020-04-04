package com.dobby.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    /**
     * 配置一个 RestTemplate 对象
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
