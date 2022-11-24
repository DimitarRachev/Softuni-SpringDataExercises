package com.example.cardealer.service;

import java.util.List;

import com.example.cardealer.model.dto.exportDto.CustomerWithSalesDto;
import com.example.cardealer.model.dto.exportDto.CustomerExportWrapperDto;
import com.example.cardealer.model.dto.exportDto.CustomerWithSalesWrapperDto;
import com.example.cardealer.model.entity.Customer;


public interface CustomerService {
  long count();

  void save(Customer customer);

  Customer getRandomCustomer();

  CustomerExportWrapperDto findAllOrderedByBirthDateAndYoung();

  CustomerWithSalesWrapperDto getTotalSalesByCustomer();
}
