package com.example.cardealer.service.impl;

import java.util.List;

import javax.transaction.Transactional;


import com.example.cardealer.model.dto.exportDto.SaleDetailsDto;
import com.example.cardealer.model.dto.exportDto.SaleDetailsWrapperDto;
import com.example.cardealer.model.entity.Car;
import com.example.cardealer.model.entity.Customer;
import com.example.cardealer.model.entity.Sale;
import com.example.cardealer.repository.SaleRepository;
import com.example.cardealer.service.CustomerService;
import com.example.cardealer.service.SaleService;
import com.example.cardealer.util.CustomMapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
  private final SaleRepository saleRepository;
  private final CustomerService customerService;
  private final ModelMapper mapper;


  @Override public void save(Car car, Customer customer, Double discount) {
    Sale sale = new Sale(car, customer, discount);
    customer.getSales().add(sale);
 customerService.save(customer);
 saleRepository.save(sale);
  }

  @Transactional
  @Override public SaleDetailsWrapperDto getSalesWithDiscount() {
    SaleDetailsWrapperDto toReturn = new SaleDetailsWrapperDto();
     saleRepository
      .findAll()
      .stream()
      .map(CustomMapper::saleToSaleDetails)
      .forEach(s -> toReturn.getSales().add(s));
    return toReturn;
  }

  @Override public long count() {
    return saleRepository.count();
  }
}
