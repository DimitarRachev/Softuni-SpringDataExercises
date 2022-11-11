package com.example.modelmapper.service.impl;

import java.util.Set;

import com.example.modelmapper.model.ShoppingCart;
import com.example.modelmapper.model.entity.Game;
import com.example.modelmapper.model.entity.User;
import com.example.modelmapper.service.GameService;
import com.example.modelmapper.service.ShoppingCartService;
import com.example.modelmapper.service.UserService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
  private final ShoppingCart shoppingCart;
  private final UserService userService;
  private final GameService gameService;

  @Override public String checkout() {
    Set<Game> gamesToBuy = shoppingCart.getGames();
    if (gamesToBuy.isEmpty()) {
      return "No games to buy";
    }
    User loggedUser = userService.getLoggedUser();
    StringBuilder response = new StringBuilder().append("Successfully bought games:").append(System.lineSeparator());
    for (Game game : gamesToBuy) {
      response.append(" -").append(game.getTitle()).append(System.lineSeparator());
      loggedUser.addGame(game);
    }
    userService.save(loggedUser);
    shoppingCart.emptyCart();
    return response.toString();
  }

  @Override public void addGameToCart(String title) {
    User loggedUser = userService.getLoggedUser();
    Game game = gameService.getByTitle(title);
    if (shoppingCart.getGames().contains(game) || loggedUser.getGames().contains(game)) {
      throw new IllegalArgumentException("You cannot buy " + title + " twice.");
    }
    shoppingCart.addGame(game);
  }

  @Override public void removeItem(String title) {
    Game game = gameService.getByTitle(title);
    if (!shoppingCart.contains(game)) {
      throw new IllegalArgumentException("Game " + title + " not in the cart.");
    }
    shoppingCart.removeItem(game);
  }
}
