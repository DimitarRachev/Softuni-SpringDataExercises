package com.example.productshop.config;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {


  @Bean
  public ModelMapper getModelMapper() {
   return new ModelMapper();
  }

  @Bean
  public Random getRandom() {
    return new Random();
  }
}


