package com.example.cardealer.util;

import java.math.BigDecimal;

import com.example.cardealer.model.dto.exportDto.CarSimpleExportDto;
import com.example.cardealer.model.dto.exportDto.SaleDetailsDto;
import com.example.cardealer.model.entity.Car;
import com.example.cardealer.model.entity.Part;
import com.example.cardealer.model.entity.Sale;


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
