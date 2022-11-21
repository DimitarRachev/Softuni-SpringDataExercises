package com.example.car_dealer.repository;

import java.util.List;

import com.example.car_dealer.model.entity.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);
}
