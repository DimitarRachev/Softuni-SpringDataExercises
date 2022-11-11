package com.usersystem.config;

import org.springframework.context.annotation.Bean;

import java.util.Random;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
   public Random getRandom() {
        return new Random();
    }
}
