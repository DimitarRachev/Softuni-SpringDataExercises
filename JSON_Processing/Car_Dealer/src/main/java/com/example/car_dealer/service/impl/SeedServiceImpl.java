package com.example.car_dealer.service.impl;

import static com.example.car_dealer.constant.Path.CARS_JSON;
import static com.example.car_dealer.constant.Path.CUSTOMER_JSON;
import static com.example.car_dealer.constant.Path.PARTS_JSON;
import static com.example.car_dealer.constant.Path.SUPPLIER_JSON;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import com.example.car_dealer.model.dto.CarImportDto;
import com.example.car_dealer.model.dto.CustomerImportDto;
import com.example.car_dealer.model.dto.PartImportDto;
import com.example.car_dealer.model.dto.SupplierImportDto;
import com.example.car_dealer.model.entity.Car;
import com.example.car_dealer.model.entity.Customer;
import com.example.car_dealer.model.entity.Part;
import com.example.car_dealer.model.entity.Supplier;
import com.example.car_dealer.model.enumeration.Discount;
import com.example.car_dealer.service.CarService;
import com.example.car_dealer.service.CustomerService;
import com.example.car_dealer.service.PartService;
import com.example.car_dealer.service.SaleService;
import com.example.car_dealer.service.SeedService;
import com.example.car_dealer.service.SupplierService;
import com.example.car_dealer.util.FileManipulationFactory;
import com.google.gson.Gson;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeedServiceImpl implements SeedService {
  private final ModelMapper mapper;
  private final Gson gson;
  private final CustomerService customerService;
  private final SupplierService supplierService;
  private final PartService partService;
  private final CarService carService;
  private final SaleService saleService;

  @Override public void seedSales() {
    for (int i = 0; i < 32; i++) {
      Customer customer = customerService.getRandomCustomer();
      Car car = carService.getRandomCar();
      Double discount = Discount.getRandomDiscount();
      saleService.save(car, customer, discount);
    }
  }

  @Override public void seedCars() throws IOException {
    if (carService.count() == 0) {
      FileReader reader = FileManipulationFactory.getFileReader(CARS_JSON);
      Arrays.stream(gson
          .fromJson(reader, CarImportDto[].class))
//        .peek(c -> {
//          List<Part> randomParts = partService.getRandomParts();
//          randomParts.forEach(part -> {
//            part.getCars().add(mapper.map(c, Car.class));
//            partService.save(part);
//          });
//          c.setParts(randomParts);
//        })
        .map(c -> mapper.map(c, Car.class))
        .peek(c -> c.setParts(partService.getRandomParts()))
        .forEach(carService::save);
      reader.close();
    }
  }

  @Override public void seedParts() throws IOException {
    if (partService.count() == 0) {
      FileReader reader = FileManipulationFactory.getFileReader(PARTS_JSON);
      Arrays.stream(gson
          .fromJson(reader, PartImportDto[].class))
        .map(p -> mapper.map(p, Part.class))
        .peek(p -> p.setSupplier(supplierService.getRandomSupplier()))
        .forEach(partService::save);
      reader.close();
    }
  }

  @Override public void seedSuppliers() throws IOException {
    if (supplierService.count() == 0) {
      FileReader reader = FileManipulationFactory.getFileReader(SUPPLIER_JSON);
      Arrays.stream(gson
          .fromJson(reader, SupplierImportDto[].class))
        .map(s -> mapper.map(s, Supplier.class))
        .forEach(supplierService::save);
      reader.close();
    }
  }

  @Override public void seedCustomers() throws IOException {
    if (customerService.count() == 0) {
      FileReader reader = FileManipulationFactory.getFileReader(CUSTOMER_JSON);
      Arrays.stream(gson
          .fromJson(reader, CustomerImportDto[].class))
        .map(c -> mapper.map(c, Customer.class))
        .forEach(customerService::save);
      reader.close();
    }
  }
}
