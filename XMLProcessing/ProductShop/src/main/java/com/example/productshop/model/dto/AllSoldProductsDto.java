package com.example.productshop.model.dto;

import java.util.List;

import com.example.productshop.model.dto.importDto.ProductImportDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllSoldProductsDto {
  private Integer count;
  private List<ProductImportDto> products;

  public AllSoldProductsDto(List<ProductImportDto> products) {
    this.products = products;
    count = products.size();
  }
}
