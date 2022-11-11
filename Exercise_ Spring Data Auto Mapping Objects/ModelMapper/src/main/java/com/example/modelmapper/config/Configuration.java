package com.example.modelmapper.config;


import java.util.Scanner;

import com.example.modelmapper.model.ShoppingCart;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

  @Bean
  public ModelMapper getModelMapper() {
    return new ModelMapper();
  }

  @Bean
  public ShoppingCart getCart() {
    return new ShoppingCart();
  }

  @Bean
  public Scanner getScanner() {
    return new Scanner(System.in);
  }
}
