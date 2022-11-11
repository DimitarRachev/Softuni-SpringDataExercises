package com.example.springintro.config;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {


  @Bean
  public Scanner getScanner() {
    return new Scanner(System.in);
  }
}
