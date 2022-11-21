package com.example.car_dealer.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.example.car_dealer.model.dto.SaleDetailsDto;
import com.example.car_dealer.model.entity.Car;
import com.example.car_dealer.model.entity.Customer;
import com.example.car_dealer.model.entity.Sale;
import com.example.car_dealer.repository.SaleRepository;
import com.example.car_dealer.service.CustomerService;
import com.example.car_dealer.service.SaleService;
import com.example.car_dealer.util.CustomMapper;

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
  @Override public List<SaleDetailsDto> getSalesWithDiscount() {

    return saleRepository
      .findAll()
      .stream()
      .map(CustomMapper::saleToSaleDetails)
      .toList();
  }
}
