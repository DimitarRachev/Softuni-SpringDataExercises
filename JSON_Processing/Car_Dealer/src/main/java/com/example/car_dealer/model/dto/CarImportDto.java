package com.example.car_dealer.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.car_dealer.model.entity.Part;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarImportDto {
  private String make;
  private String model;
  private Long travelledDistance;
  private List<Part> parts;

  public CarImportDto() {
    parts = new ArrayList<>();
  }
}
