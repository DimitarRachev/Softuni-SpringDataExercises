package com.example.car_dealer.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWithSalesDto {
  private String fullName;
  private Long boughtCars;
  private Double spentMoney;

  }
