package com.example.car_dealer.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDetailsDto {
  private CarSimpleExportDto car;
  private String customerName;
  private Double discount;
  private BigDecimal price;
  private BigDecimal priceWithDiscount;
}
