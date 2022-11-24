package com.example.cardealer.model.dto.importDto;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.example.cardealer.util.LocalDateTimeAdapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerImportDto {
  @XmlAttribute(name = "name")
  private String name;
  @XmlElement(name = "birth-date")
  @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  private LocalDateTime birthDate;
  @XmlElement(name = "is-young-driver")
  private Boolean isYoungDriver;
}
