package com.example.cardealer.model.dto.exportDto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "customer")
public class CustomerWithSalesDto {
  @XmlAttribute(name = "full-name")
  private String fullName;
  @XmlAttribute(name = "bought-cars")
  private Long boughtCars;
  @XmlAttribute(name = "spent-money")
  private Double spentMoney;

  }
