package com.example.productshop.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

public interface SeedService {
  default void seedAll() throws IOException, JAXBException {
    seedCategories();
    seedUsers();
    seedProducts();
  }

  void seedCategories() throws FileNotFoundException, JAXBException;

  void seedProducts() throws FileNotFoundException, JAXBException;

  void seedUsers() throws IOException, JAXBException;
}