package com.example.cardealer.service;

import java.util.List;

import com.example.cardealer.model.dto.exportDto.CarWithPartsListDto;
import com.example.cardealer.model.dto.exportDto.CarExportWrapperDto;
import com.example.cardealer.model.dto.exportDto.CarWithPartsListWrapperDto;
import com.example.cardealer.model.entity.Car;


public interface CarService {
  long count();

  void save(Car car);

  Car getRandomCar();

  CarExportWrapperDto getAllToyotas(String make);

  CarWithPartsListWrapperDto getCarsWithPartsList();
}
