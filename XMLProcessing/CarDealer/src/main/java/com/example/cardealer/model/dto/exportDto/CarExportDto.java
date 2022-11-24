package com.example.cardealer.model.dto.exportDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarExportDto {
  @XmlAttribute
  private Long id;
  @XmlAttribute
  private String make;
  @XmlAttribute
  private String model;
  @XmlAttribute(name = "travelled-distance")
  private Long travelledDistance;
}
