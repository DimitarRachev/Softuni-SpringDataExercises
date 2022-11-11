package com.example.modelmapper;

import java.util.Scanner;

import com.example.modelmapper.controller.Controller;
import com.example.modelmapper.exception.InvalidCommandException;
import com.example.modelmapper.exception.InvalidCredentialsException;
import com.example.modelmapper.exception.InvalidEmailException;
import com.example.modelmapper.exception.InvalidPasswordException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class ModelMapperApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(ModelMapperApplication.class, args);
  }

  private final Scanner scanner;
  private final Controller controller;

  @Override public void run(String... args) throws Exception {
    while (true) {
      String command = scanner.nextLine();
      String result;
      try {
        result = controller.process(command);
      } catch (InvalidCommandException | InvalidCredentialsException | IllegalArgumentException
               | InvalidEmailException | IllegalAccessException | InvalidPasswordException e) {
        result = e.getMessage();
      }
      if (result.equals("Exit")) {
        break;
      }
      System.out.println(result);
    }
  }
}
