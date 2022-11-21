package com.example.car_dealer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarExportDto {
  private Long id;
  private String make;
  private String model;
  private Long travelledDistance;
}
