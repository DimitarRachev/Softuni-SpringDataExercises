package com.example.car_dealer.service.impl;

import java.util.List;
import java.util.Random;

import com.example.car_dealer.model.dto.CustomerExportDto;
import com.example.car_dealer.model.dto.CustomerWithSalesDto;
import com.example.car_dealer.model.entity.Customer;
import com.example.car_dealer.repository.CustomerRepository;
import com.example.car_dealer.service.CustomerService;

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

  @Override public List<CustomerExportDto> findAllOrderedByBirthDateAndYoung() {
   return customerRepository
      .findAllByOrderByBirthDateAscIsYoungDriverDesc()
      .stream()
      .map(c -> mapper.map(c, CustomerExportDto.class))
      .toList();
  }

  @Override public List<CustomerWithSalesDto> getTotalSalesByCustomer() {
    return customerRepository.findCustomerWithSales();
  }
}
