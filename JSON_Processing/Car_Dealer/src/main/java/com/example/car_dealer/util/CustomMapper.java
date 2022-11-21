package com.example.car_dealer.util;

import java.math.BigDecimal;

import com.example.car_dealer.model.dto.CarSimpleExportDto;
import com.example.car_dealer.model.dto.SaleDetailsDto;
import com.example.car_dealer.model.entity.Car;
import com.example.car_dealer.model.entity.Part;
import com.example.car_dealer.model.entity.Sale;

public class CustomMapper {
  public static SaleDetailsDto saleToSaleDetails(Sale sale) {
    return SaleDetailsDto
      .builder()
      .customerName(sale.getCustomer().getName())
      .car(carToCarSimpleExportDto(sale.getCar()))
      .discount(sale.getDiscount())
      .price(sale.getCar().getParts().stream().map(Part::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
      .priceWithDiscount((sale.getCar().getParts().stream().map(Part::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add)).multiply(BigDecimal.valueOf(sale.getDiscount())))
      .build();
  }

  private static CarSimpleExportDto carToCarSimpleExportDto(Car car) {
    return CarSimpleExportDto
      .builder()
      .make(car.getMake())
      .model(car.getModel())
      .travelledDistance(car.getTravelledDistance())
      .build();
  }
}
