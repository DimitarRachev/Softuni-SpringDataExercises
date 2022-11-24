package com.example.cardealer.model.dto.exportDto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@XmlRootElement(name="cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWithPartsListWrapperDto {
  @XmlElement(name="car")
  private List<CarWithPartsListDto> cars;

  public CarWithPartsListWrapperDto() {
    cars = new ArrayList<CarWithPartsListDto>();
  }
}
