package com.example.car_dealer.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarWithPartsListDto {
  private String make;
  private String model;
  private Long travelledDistance;
  private List<PartSimpleDto> parts;
}
