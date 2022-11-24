package com.example.productshop.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryInfoDto {
  private String category;
  private long categoryCount;
  private BigDecimal averagePrice;
  private BigDecimal totalRevenue;
}
