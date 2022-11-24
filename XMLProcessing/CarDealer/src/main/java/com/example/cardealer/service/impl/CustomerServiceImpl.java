package com.example.cardealer.service.impl;

import java.util.List;
import java.util.Random;


import com.example.cardealer.model.dto.exportDto.CustomerExportDto;
import com.example.cardealer.model.dto.exportDto.CustomerWithSalesDto;
import com.example.cardealer.model.dto.exportDto.CustomerExportWrapperDto;
import com.example.cardealer.model.dto.exportDto.CustomerWithSalesWrapperDto;
import com.example.cardealer.model.entity.Customer;
import com.example.cardealer.repository.CustomerRepository;
import com.example.cardealer.service.CustomerService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository customerRepository;
  private final Random random;
  private final ModelMapper mapper;

  @Override public long count() {
    return customerRepository.count();
  }

  @Override public void save(Customer customer) {
    customerRepository.save(customer);
  }

  @Override public Customer getRandomCustomer() {
    long index = random.nextLong(customerRepository.count()) + 1L;
    return customerRepository.findById(index).orElseThrow(RuntimeException::new);
  }

  @Override public CustomerExportWrapperDto findAllOrderedByBirthDateAndYoung() {
    CustomerExportWrapperDto toReturn = new CustomerExportWrapperDto();
    customerRepository
      .findAllByOrderByBirthDateAscIsYoungDriverDesc()
      .stream()
      .map(c -> mapper.map(c, CustomerExportDto.class))
      .forEach(s -> toReturn.getCustomers().add(s));
    return toReturn;
  }

  @Override public CustomerWithSalesWrapperDto getTotalSalesByCustomer() {
    CustomerWithSalesWrapperDto toReturn = new CustomerWithSalesWrapperDto();
    customerRepository
      .findCustomerWithSales()
      .forEach(s -> toReturn.getCustomers().add(s));
    return toReturn;
  }
}
