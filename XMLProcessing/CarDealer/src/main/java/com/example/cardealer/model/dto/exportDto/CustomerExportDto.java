package com.example.cardealer.model.dto.exportDto;

import java.time.LocalDateTime;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerExportDto {
  @XmlElement(name = "id")
  private Long id;
  @XmlElement(name = "name")
  private String name;
  @XmlElement(name = "birth-date")
  private LocalDateTime birthDate;
  @XmlElement(name = "is-young-driver")
  private Boolean isYoungDriver;


}
