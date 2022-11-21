package com.example.product_shop.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Random;

import com.example.product_shop.model.dto.CategoryImportDto;
import com.example.product_shop.model.dto.ProductImportDto;
import com.example.product_shop.model.dto.UserImportDto;
import com.example.product_shop.model.entity.Category;
import com.example.product_shop.model.entity.Product;
import com.example.product_shop.model.entity.User;
import com.example.product_shop.service.CategoryService;
import com.example.product_shop.service.ProductService;
import com.example.product_shop.service.SeedService;
import com.example.product_shop.service.UserService;
import com.google.gson.Gson;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeedServiceImpl implements SeedService {
  private static final String CATEGORIES_SUFFIX = "/categories.json";
  private static final String USERS_SUFFIX = "/users.json";
  private static final String PRODUCTS_SUFFIX = "/products.json";
  private static final String BASE_PATH = "Product_Shop/src/main/resources/files";

  private final UserService userService;
  private final ProductService productService;
  private final CategoryService categoryService;
  private final Gson gson;
  private final Random rand;
private final ModelMapper modelMapper;

  @Override public void seedCategories() throws FileNotFoundException {
    if (categoryService.count() == 0L) {
      File categoryFile = new File(BASE_PATH + CATEGORIES_SUFFIX);
      Arrays.stream(gson.fromJson(new FileReader(categoryFile), CategoryImportDto[].class))
        .map(c -> modelMapper.map(c, Category.class))
        .forEach(categoryService::save);
    }
  }

  @Override public void seedProducts() throws FileNotFoundException {
    if (productService.count() == 0L) {


      File productsFile = new File(BASE_PATH + PRODUCTS_SUFFIX);
      Arrays.stream(gson.fromJson(new FileReader(productsFile), ProductImportDto[].class))
        .map(p -> modelMapper.map(p, Product.class))
        .peek(p -> p.setSeller(userService.getRandomUser()))
        .peek(p -> p.setBuyer(rand.nextInt(2) == 1 ? null : userService.getRandomUser()))
        .peek(p -> p.setCategories(categoryService.getRandomCategories()))
        .forEach(productService::save);
    }
  }

  @Override public void seedUsers() throws IOException {
    if (userService.count() == 0L) {
      File userFile = new File(BASE_PATH + USERS_SUFFIX);
      Arrays.stream(gson.fromJson(new FileReader(userFile), UserImportDto[].class))
        .map(e -> modelMapper.map(e, User.class))
        .forEach(userService::save);
    }
  }
}
