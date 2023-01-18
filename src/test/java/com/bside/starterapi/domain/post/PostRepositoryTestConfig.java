package com.bside.starterapi.domain.post;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostRepositoryTestConfig {

    @Bean
    public PostEventHandler handler() {
        return new PostEventHandler();
    }
}
