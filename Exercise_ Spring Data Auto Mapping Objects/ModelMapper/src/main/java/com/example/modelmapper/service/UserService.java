package com.example.modelmapper.service;

import java.util.List;

import com.example.modelmapper.exception.InvalidCommandException;
import com.example.modelmapper.exception.InvalidCredentialsException;
import com.example.modelmapper.exception.InvalidEmailException;
import com.example.modelmapper.model.dto.GameShortInfo;
import com.example.modelmapper.model.dto.UserLoginDto;
import com.example.modelmapper.model.dto.UserRequest;
import com.example.modelmapper.model.entity.User;

public interface UserService {

  User getLoggedUser();

  void register(UserRequest userRequest) throws InvalidEmailException;

  void logout() throws InvalidCommandException;

  void login(UserLoginDto userLoginDto) throws InvalidCredentialsException;

  boolean isLoggedAdmin();

  List<GameShortInfo> getAllOwnedGames();

  void save(User user);
}
