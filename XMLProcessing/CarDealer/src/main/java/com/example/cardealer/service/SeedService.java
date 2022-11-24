package com.example.cardealer.service;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import lombok.RequiredArgsConstructor;


public interface SeedService {
  default void seedAll() throws IOException, JAXBException {
    seedSuppliers();
    seedParts();
    seedCars();
    seedCustomers();
    seedSales();
  }

   void seedSales();

   void seedCars() throws IOException, JAXBException;

   void seedParts() throws IOException, JAXBException;

   void seedSuppliers() throws IOException, JAXBException;

   void seedCustomers() throws IOException, JAXBException;
}
