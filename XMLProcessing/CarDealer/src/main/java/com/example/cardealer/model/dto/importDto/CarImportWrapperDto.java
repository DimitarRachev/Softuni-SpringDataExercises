package com.example.cardealer.model.dto.importDto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@XmlRootElement(name="cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportWrapperDto {

  @XmlElement(name="car")
  private List<CarImportDto> cars;

  public CarImportWrapperDto() {
    cars = new ArrayList<CarImportDto>();
  }
}
