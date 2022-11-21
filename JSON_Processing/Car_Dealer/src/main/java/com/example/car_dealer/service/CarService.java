package com.example.car_dealer.service;

import java.util.Collection;
import java.util.List;

import com.example.car_dealer.model.dto.CarExportDto;
import com.example.car_dealer.model.dto.CarWithPartsListDto;
import com.example.car_dealer.model.entity.Car;

public interface CarService {
  long count();

  void save(Car car);

  Car getRandomCar();

  List<CarExportDto> getAllToyotas(String make);

  List<CarWithPartsListDto> getCarsWithPartsList();
}
