package com.example.modelmapper.model;

import java.util.HashSet;
import java.util.Set;

import com.example.modelmapper.model.entity.Game;

import lombok.Data;

@Data
public class ShoppingCart {
  Set<Game> games;

  public ShoppingCart() {
    games = new HashSet<>();
  }

  public void emptyCart() {
    games.clear();
  }

  public void addGame(Game game) {
    games.add(game);
  }

  public boolean contains(Game game) {
    return games.contains(game);
  }

  public void removeItem(Game game) {
    games.remove(game);
  }
}
