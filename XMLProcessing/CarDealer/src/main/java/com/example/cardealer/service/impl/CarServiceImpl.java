package com.example.cardealer.service.impl;

import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;


import com.example.cardealer.model.dto.exportDto.CarExportDto;
import com.example.cardealer.model.dto.exportDto.CarWithPartsListDto;
import com.example.cardealer.model.dto.exportDto.CarExportWrapperDto;
import com.example.cardealer.model.dto.exportDto.CarWithPartsListWrapperDto;
import com.example.cardealer.model.entity.Car;
import com.example.cardealer.repository.CarRepository;
import com.example.cardealer.service.CarService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
  private final CarRepository carRepository;
  private final Random random;
  private final ModelMapper mapper;

  @Override public long count() {
    return carRepository.count();
  }

  @Override public void save(Car car) {
    carRepository.save(car);
  }

  @Override public Car getRandomCar() {
    long id = random.nextLong(carRepository.count()) + 1L;
    return carRepository.findById(id).orElseThrow(RuntimeException::new);
  }

  @Override public CarExportWrapperDto getAllToyotas(String make) {
    CarExportWrapperDto toReturn = new CarExportWrapperDto();
     carRepository
      .findAllByMakeOrderByModelAscTravelledDistanceDesc(make)
      .stream()
      .map(c -> mapper.map(c, CarExportDto.class))
      .forEach(s -> toReturn.getCars().add(s));
     return toReturn;
  }

  @Transactional
  @Override public CarWithPartsListWrapperDto getCarsWithPartsList() {
    CarWithPartsListWrapperDto toReturn = new CarWithPartsListWrapperDto();
   carRepository.
      findAll()
      .stream()
      .map(c -> mapper.map(c, CarWithPartsListDto.class))
      .forEach(s ->toReturn.getCars().add(s));
    return toReturn;
  }
}
