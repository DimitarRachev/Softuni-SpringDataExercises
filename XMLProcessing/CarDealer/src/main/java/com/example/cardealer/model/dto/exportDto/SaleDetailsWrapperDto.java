package com.example.cardealer.model.dto.exportDto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleDetailsWrapperDto {
  @XmlElement(name = "sale")
  private List<SaleDetailsDto> sales;

  public SaleDetailsWrapperDto() {
    sales = new ArrayList<SaleDetailsDto>();
  }
}
