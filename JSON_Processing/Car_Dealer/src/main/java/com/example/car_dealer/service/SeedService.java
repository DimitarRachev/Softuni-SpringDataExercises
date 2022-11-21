package com.example.car_dealer.service;

import java.io.IOException;

import lombok.RequiredArgsConstructor;


public interface SeedService {
  default void seedAll() throws IOException {
    seedSuppliers();
    seedParts();
    seedCars();
    seedCustomers();
    seedSales();
  }

   void seedSales();

   void seedCars() throws IOException;

   void seedParts() throws IOException;

   void seedSuppliers() throws IOException;

   void seedCustomers() throws IOException;
}
