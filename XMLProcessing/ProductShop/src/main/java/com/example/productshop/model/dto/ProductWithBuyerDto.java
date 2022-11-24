package com.example.productshop.model.dto;


import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductWithBuyerDto {
  private String name;
  private BigDecimal price;
  private String buyerFirstName;
  private String buyerLastName;
}
