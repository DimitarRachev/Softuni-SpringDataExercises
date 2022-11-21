package com.example.car_dealer.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.example.car_dealer.model.entity.Car;
import com.example.car_dealer.model.entity.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartImportDto {
  private String name;

  private BigDecimal price;

  private Integer quantity;

//  private List<Car> cars;

  private Supplier supplier;

//  public PartImportDto() {
//    cars = new ArrayList<>();
//  }
}
