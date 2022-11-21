package com.example.product_shop.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerWithProductsDto {
  private String firstName;
  private String lastName;
  private List<ProductWithBuyerDto> products;
}
