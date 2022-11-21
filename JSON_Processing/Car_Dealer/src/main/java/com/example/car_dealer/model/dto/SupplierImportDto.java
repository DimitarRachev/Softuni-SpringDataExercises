package com.example.car_dealer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierImportDto {
  private String name;

  private Boolean isImporter;
}
