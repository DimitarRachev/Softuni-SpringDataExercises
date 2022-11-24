package com.example.cardealer.service;

import java.util.List;

import com.example.cardealer.model.dto.exportDto.SaleDetailsDto;
import com.example.cardealer.model.dto.exportDto.SaleDetailsWrapperDto;
import com.example.cardealer.model.entity.Car;
import com.example.cardealer.model.entity.Customer;


public interface SaleService {

  void save(Car car, Customer customer, Double discount);

  SaleDetailsWrapperDto getSalesWithDiscount();

  long count();
}
