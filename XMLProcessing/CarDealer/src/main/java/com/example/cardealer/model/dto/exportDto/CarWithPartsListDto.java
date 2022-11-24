package com.example.cardealer.model.dto.exportDto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWithPartsListDto {
  @XmlAttribute
  private String make;
  @XmlAttribute
  private String model;
  @XmlAttribute(name = "travelled-distance")
  private Long travelledDistance;
  @XmlElementWrapper(name = "parts")
  private List<PartSimpleDto> parts;
}
