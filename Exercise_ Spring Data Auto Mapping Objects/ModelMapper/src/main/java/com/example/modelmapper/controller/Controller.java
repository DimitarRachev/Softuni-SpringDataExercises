package com.example.modelmapper.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.example.modelmapper.exception.InvalidCommandException;
import com.example.modelmapper.exception.InvalidCredentialsException;
import com.example.modelmapper.exception.InvalidEmailException;
import com.example.modelmapper.exception.InvalidPasswordException;
import com.example.modelmapper.model.dto.GameDTO;
import com.example.modelmapper.model.dto.GameRequest;
import com.example.modelmapper.model.dto.GameShortInfo;
import com.example.modelmapper.model.dto.UserLoginDto;
import com.example.modelmapper.model.dto.UserRequest;
import com.example.modelmapper.service.GameService;
import com.example.modelmapper.service.ShoppingCartService;
import com.example.modelmapper.service.UserService;
import com.example.modelmapper.util.Validator;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Controller {
  private static final String LOGIN_USER = "LoginUser";
  private static final String LOGOUT = "Logout";
  private static final String REGISTER_USER = "RegisterUser";
  private static final String ADD_GAME = "AddGame";
  private static final String EDIT_GAME = "EditGame";
  private static final String DELETE_GAME = "DeleteGame";
  private static final String ALL_GAMES = "AllGames";
  private static final String DETAIL_GAME = "DetailGame";
  private static final String OWNED_GAMES = "OwnedGames";
  private static final String ADD_ITEM = "AddItem";
  private static final String REMOVE_ITEM = "RemoveItem";
  private static final String BUY_ITEM = "BuyItem";
  private static final String EXIT = "Exit";

  private final UserService userService;
  private final GameService gameService;
  private final Validator validator;
  private final ShoppingCartService shoppingCartService;

  public String process(String line)
    throws InvalidCommandException, InvalidPasswordException, InvalidEmailException,
    InvalidCredentialsException, IllegalAccessException {
    String[] input = line.split("\\|");
    String command = input[0];
    return switch (command) {
      case LOGIN_USER -> login(input);
      case LOGOUT -> logout();
      case REGISTER_USER -> register(input);
      case ADD_GAME -> addGame(input);
      case EDIT_GAME -> editGame(input);
      case DELETE_GAME -> deleteGame(input);
      case ALL_GAMES -> showAllGames();
      case DETAIL_GAME -> showDetailsFor(input[1]);
      case OWNED_GAMES -> showOwnedGames();
      case ADD_ITEM -> addItem(input[1]);
      case REMOVE_ITEM -> removeItem(input[1]);
      case BUY_ITEM -> checkout();
      case EXIT -> EXIT;
      default -> throw new InvalidCommandException("Command not recognized!");
    };
  }

  private String removeItem(String title) {
    shoppingCartService.removeItem(title);
    return title + " removed from cart.";
  }

  private String addItem(String title) {
    shoppingCartService.addGameToCart(title);
    return title + " added to cart.";
  }

  private String checkout() {
    return shoppingCartService.checkout();
  }

  private String showDetailsFor(String gameName) {
    return gameService.getDetailsFor(gameName).toString();
  }

  private String showOwnedGames() {
    List<GameShortInfo> allOwnedGames = userService.getAllOwnedGames();
    return allOwnedGames == null
      ? "No owned games!"
      : allOwnedGames
      .stream()
      .map(GameShortInfo::getTitle)
      .collect(Collectors.joining(System.lineSeparator()));
  }

  private String showAllGames() {
    return gameService.getAllGames()
      .stream()
      .map(GameShortInfo::toString)
      .collect(Collectors.joining(System.lineSeparator()));
  }

  private String editGame(String[] input) {
    Long id = Long.parseLong(input[1]);
    GameDTO gameDTO = gameService.getById(id);
    for (int i = 2; i < input.length; i++) {
      String[] toEdit = input[i].split("=");
      switch (toEdit[0]) {
        case "title" -> gameDTO.setTitle(validator.validateTitle(toEdit[1]));
        case "trailer" -> gameDTO.setTrailer(validator.validateTrailer(toEdit[1]));
        case "imageThumbnail" -> gameDTO.setImageThumbnail(validator.validateThumbnail(toEdit[1]));
        case "size" -> gameDTO.setSize(validator.validatePositive(toEdit[1]));
        case "price" -> gameDTO.setPrice(BigDecimal.valueOf(validator.validatePositive(toEdit[1])));
        case "description" -> gameDTO.setDescription(validator.validateDescription(toEdit[1]));
        case "releaseDate" -> {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
          gameDTO.setReleaseDate(LocalDate.parse(input[1], formatter));
        }
        default -> throw new IllegalArgumentException("Unknown game field: " + input[0]);
      }
    }
    gameService.save(gameDTO);
    return "Edited " + gameDTO.getTitle();
  }

  private String deleteGame(String[] input) {

    return gameService.deleteGame(Long.parseLong(input[1]));
  }

  private String addGame(String[] input) throws IllegalAccessException {
    if (!userService.isLoggedAdmin()) {
      throw new IllegalAccessException("Only admin may add games!");
    }
    String title = validator.validateTitle(input[1]);
    BigDecimal price = BigDecimal.valueOf(validator.validatePositive(input[2]));
    Double size = validator.validatePositive(input[3]);
    String trailer = validator.validateTrailer(input[4]);
    String imageThumbnail = validator.validateThumbnail(input[5]);
    String description = validator.validateDescription(input[6]);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    LocalDate releaseDate = LocalDate.parse(input[7], formatter);
    GameRequest gameRequest = new GameRequest(title, trailer, imageThumbnail, size, price, description, releaseDate);
    gameService.addGame(gameRequest);
    return "Added " + title;
  }

  private String login(String[] input) throws InvalidCredentialsException {
    UserLoginDto userLoginDto = new UserLoginDto(input[1], input[2]);
    userService.login(userLoginDto);
    return "Logged in successfully";
  }

  private String logout() throws InvalidCommandException {
    userService.logout();
    return "Logged out successfully";
  }

  private String register(String[] input) throws InvalidPasswordException, InvalidEmailException {
    String email = validator.validateEmail(input[1]);
    String password = validator.validatePassword(input[2]);
    String confirmPassword = input[3];
    if (!password.equals(confirmPassword)) {
      throw new InvalidPasswordException("Passwords must match");
    }
    String fullName = input[4];
    UserRequest userRequest = new UserRequest(email, password, fullName);
    userService.register(userRequest);
    return "Registered in successfully!";
  }
}
