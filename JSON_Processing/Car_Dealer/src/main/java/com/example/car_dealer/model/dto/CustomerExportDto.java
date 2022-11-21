package com.example.car_dealer.model.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerExportDto {
  private Long id;
  private String name;

  private LocalDateTime birthDate;

  private Boolean isYoungDriver;

  private Set<SaleSimpleDto> sales;
}
