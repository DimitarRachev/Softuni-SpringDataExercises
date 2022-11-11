package com.example.modelmapper.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import com.example.modelmapper.exception.InvalidCommandException;
import com.example.modelmapper.exception.InvalidCredentialsException;
import com.example.modelmapper.exception.InvalidEmailException;
import com.example.modelmapper.model.dto.GameShortInfo;
import com.example.modelmapper.model.dto.UserLoginDto;
import com.example.modelmapper.model.dto.UserRequest;
import com.example.modelmapper.model.entity.Game;
import com.example.modelmapper.model.entity.User;
import com.example.modelmapper.repository.UserRepository;
import com.example.modelmapper.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private User loggedUser;
  private final UserRepository userRepository;
  private final ModelMapper modelMapper;

  @Override public User getLoggedUser() {
    return loggedUser ;
  }

  @Override public void register(UserRequest userRequest) throws InvalidEmailException {
    if (userRepository.existsByEmail(userRequest.getEmail())) {
      throw new InvalidEmailException("Email already exists!");
    }
    User user = modelMapper.map(userRequest, User.class);
    if (userRepository.count() == 0) {
      user.setAdmin(true);
    }
    userRepository.save(user);
    loggedUser = userRepository.save(user);
  }

  @Override public void logout() throws InvalidCommandException {
    if (loggedUser == null) {
      throw new InvalidCommandException("You are not logged in!");
    }
    loggedUser = null;
  }

  @Override public void login(UserLoginDto userLoginDto) throws InvalidCredentialsException {
    User user = userRepository
      .findByEmail(userLoginDto.getEmail())
      .orElseThrow(
        () -> new InvalidCredentialsException("No user with email " + userLoginDto.getEmail() + " found in database!"));
    if (!user.getPassword().equals(userLoginDto.getPassword())) {
      throw new InvalidCredentialsException("Username/password mismatch");
    }
    loggedUser = user;
  }

  @Override public boolean isLoggedAdmin() {
    return loggedUser!= null && loggedUser.isAdmin();
  }

  @Override public List<GameShortInfo> getAllOwnedGames() {
    if (loggedUser == null) {
      throw new IllegalArgumentException("You must be logged");
    }
    List<Game> games = loggedUser.getGames();
    return  games.isEmpty()
      ? null
      : games
      .stream()
      .map(g -> modelMapper.map(g, GameShortInfo.class))
      .collect(Collectors.toList());
  }

  @Override public void save(User user) {
    userRepository.save(user);
  }
}