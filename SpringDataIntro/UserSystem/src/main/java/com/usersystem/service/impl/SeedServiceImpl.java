package com.usersystem.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.usersystem.model.entity.Town;
import com.usersystem.model.entity.User;
import com.usersystem.repository.UserRepository;
import com.usersystem.service.SeedService;
import com.usersystem.service.TownService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SeedServiceImpl implements SeedService {


  private static final String RESOURCE_PATH = "UserSystem/src/main/resources/files/";
  private static final String USERS_FILE_NAME = RESOURCE_PATH + "users.txt";
  private static final String TOWNS_FILE_NAME = RESOURCE_PATH + "towns.txt";


  private final UserRepository userRepository;

  private final TownService townService;


  @Override
  public void seedAll() throws IOException {
    seedTowns();
    seedUsers();
  }


  private void seedTowns() throws IOException {
    if (townService.count() > 0) {
      return;
    }
    Files.readAllLines(Path.of(TOWNS_FILE_NAME))
      .stream()
      .filter(s -> !s.isBlank())
      .map(s -> s.split(","))
      .map(town -> new Town(town[0], town[1]))
      .forEach(townService::save);
  }


  private void seedUsers() throws IOException {
    if (userRepository.count() > 0) {
      return;
    }
    Files.readAllLines(Path.of(USERS_FILE_NAME))
      .stream()
      .filter(s -> !s.isBlank())
      .map(this::getUserObject)
      .forEach(userRepository::save);

  }

  private User getUserObject(String line) {
    String[] userParts = line.split(",");

    String firstName = userParts[0];
    String lastName = userParts[1];
    String username = userParts[2];
    String password = userParts[3];
    String email = userParts[4];
    int age = Integer.parseInt(userParts[5]);
    Town bornTown = townService.getRandomTown();
    Town currentTown = townService.getRandomTown();
    LocalDate date = LocalDate.parse(userParts[6], DateTimeFormatter.ofPattern("d/M/yyyy"));
    LocalTime time = LocalTime.parse(userParts[7]);
    LocalDateTime lastTimeLoggedIn = LocalDateTime.of(date, time);
    return  User.builder()
            .firstName(firstName)
            .lastName(lastName)
            .username(username)
            .password(password)
            .email(email)
            .age(age)
            .bornTown(bornTown)
            .currentTown(currentTown)
            .lastTimeLoggedIn(lastTimeLoggedIn)
            .build();
  }

}
