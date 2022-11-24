package com.example.productshop.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.example.productshop.model.dto.importDto.CategoryImportDto;
import com.example.productshop.model.dto.importDto.ProductImportWrapperDto;
import com.example.productshop.model.dto.importDto.UserImportWrapperDto;
import com.example.productshop.model.entity.Category;
import com.example.productshop.model.entity.Product;
import com.example.productshop.model.entity.User;
import com.example.productshop.service.CategoryService;
import com.example.productshop.service.ProductService;
import com.example.productshop.service.SeedService;
import com.example.productshop.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeedServiceImpl implements SeedService {
  private static final String CATEGORIES_SUFFIX = "/categories.xml";
  private static final String USERS_SUFFIX = "/users.xml";
  private static final String PRODUCTS_SUFFIX = "/products.xml";
  private static final String BASE_PATH = "ProductShop/src/main/resources/files";

  private final UserService userService;
  private final ProductService productService;
  private final CategoryService categoryService;
  private final Random rand;
  private final ModelMapper modelMapper;

  @Override public void seedCategories() throws FileNotFoundException, JAXBException {
    if (categoryService.count() == 0L) {
      File categoryFile = new File(BASE_PATH + CATEGORIES_SUFFIX);
      JAXBContext context = JAXBContext.newInstance(CategoryImportDto.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      CategoryImportDto unmarshal = (CategoryImportDto) unmarshaller.unmarshal(new FileReader(categoryFile));
      unmarshal.getCategories().stream()
        .map(c -> modelMapper.map(c, Category.class))
        .forEach(categoryService::save);
    }
  }

  @Override public void seedProducts() throws FileNotFoundException, JAXBException {
    if (productService.count() == 0L) {
      File productsFile = new File(BASE_PATH + PRODUCTS_SUFFIX);
      JAXBContext context = JAXBContext.newInstance(ProductImportWrapperDto.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      ProductImportWrapperDto unmarshal =
        (ProductImportWrapperDto) unmarshaller.unmarshal(new FileReader(productsFile));
        unmarshal.getProducts().stream()
              .map(p -> modelMapper.map(p, Product.class))
              .peek(p -> p.setSeller(userService.getRandomUser()))
              .peek(p -> p.setBuyer(rand.nextInt(2) == 1 ? null : userService.getRandomUser()))
              .peek(p -> p.setCategories(categoryService.getRandomCategories()))
              .forEach(productService::save);
    }
  }

  @Override public void seedUsers() throws IOException, JAXBException {
    if (userService.count() == 0L) {
      File userFile = new File(BASE_PATH + USERS_SUFFIX);
      JAXBContext context = JAXBContext.newInstance(UserImportWrapperDto.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      UserImportWrapperDto unmarshal = (UserImportWrapperDto) unmarshaller.unmarshal(userFile);
      unmarshal
        .getUsers()
        .stream()
              .map(e -> modelMapper.map(e, User.class))
              .forEach(userService::save);
    }
  }
}
