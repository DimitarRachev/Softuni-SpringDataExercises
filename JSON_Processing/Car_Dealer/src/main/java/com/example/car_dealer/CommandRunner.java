package com.example.car_dealer;

import com.example.car_dealer.service.CarService;
import com.example.car_dealer.service.CustomerService;
import com.example.car_dealer.service.SaleService;
import com.example.car_dealer.service.SeedService;
import com.example.car_dealer.service.SupplierService;
import com.google.gson.Gson;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommandRunner implements CommandLineRunner {
  private final SeedService seedService;
  private final CustomerService customerService;
  private final SupplierService supplierService;
  private final SaleService saleService;
  private final CarService carService;
  private final Gson gson;

  @Override public void run(String... args) throws Exception {
    seedService.seedAll();
    //    orderedCustomers();
    //    getAllByMaker("Toyota");
    //    getLocalSuppliers();
    //    getCarsWithPartsList();
    //    getTotalSalesByCustomer();
    getSalesWithDiscount();
  }

  private void getSalesWithDiscount() {
    saleService
      .getSalesWithDiscount()
      .stream()
      .map(gson::toJson)
      .forEach(System.out::println);
  }

  private void getTotalSalesByCustomer() {
    customerService
      .getTotalSalesByCustomer()
      .stream()
      .map(gson::toJson)
      .forEach(System.out::println);
  }

  private void getCarsWithPartsList() {
    carService
      .getCarsWithPartsList()
      .stream()
      .map(gson::toJson)
      .forEach(System.out::println);
  }

  private void getLocalSuppliers() {
    supplierService
      .getLocalSuppliers()
      .stream()
      .map(gson::toJson)
      .forEach(System.out::println);
  }

  private void getAllByMaker(String make) {
    carService
      .getAllToyotas(make)
      .stream()
      .map(gson::toJson)
      .forEach(System.out::println);
  }

  private void orderedCustomers() {
    customerService
      .findAllOrderedByBirthDateAndYoung()
      .stream()
      .map(gson::toJson)
      .forEach(System.out::println);
  }
}
