package com.example.car_dealer.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerImportDto {
  private String name;

  private LocalDateTime birthDate;

  private Boolean isYoungDriver;
}
