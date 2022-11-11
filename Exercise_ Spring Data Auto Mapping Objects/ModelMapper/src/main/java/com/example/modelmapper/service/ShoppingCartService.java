package com.example.modelmapper.service;

public interface ShoppingCartService {
  String checkout();

  void addGameToCart(String s);

  void removeItem(String title);
}
