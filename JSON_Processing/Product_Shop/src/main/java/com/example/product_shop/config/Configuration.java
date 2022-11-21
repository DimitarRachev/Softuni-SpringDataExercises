package com.example.product_shop.config;

import java.util.Random;

import com.example.product_shop.model.dto.UserWithProductsDto;
import com.example.product_shop.model.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

  @Bean
  public Gson getGson() {
    return new GsonBuilder().setPrettyPrinting().create();
  }

  @Bean
  public ModelMapper getModelMapper() {
   return new ModelMapper();
  }

  @Bean
  public Random getRandom() {
    return new Random();
  }
}


