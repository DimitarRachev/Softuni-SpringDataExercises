package com.example.car_dealer.config;

import java.time.LocalDateTime;
import java.util.Random;

import com.example.car_dealer.model.dto.SupplierSimpleExportDto;
import com.example.car_dealer.model.entity.Supplier;
import com.example.car_dealer.util.LocalDateTimeDeserializer;
import com.example.car_dealer.util.LocalDateTimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
  @Bean
  public Gson getGson() {
    return new GsonBuilder()
      .setPrettyPrinting()
      .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
      .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
      .create();
  }

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper mapper = new ModelMapper();

    return mapper;
  }

  @Bean
  public Random random() {
    return new Random();
  }
}
