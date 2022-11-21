package com.example.car_dealer.service;

import java.util.Collection;
import java.util.List;

import com.example.car_dealer.model.dto.CustomerExportDto;
import com.example.car_dealer.model.dto.CustomerWithSalesDto;
import com.example.car_dealer.model.entity.Customer;

public interface CustomerService {
  long count();

  void save(Customer customer);

  Customer getRandomCustomer();

  List<CustomerExportDto> findAllOrderedByBirthDateAndYoung();

  List<CustomerWithSalesDto> getTotalSalesByCustomer();
}
