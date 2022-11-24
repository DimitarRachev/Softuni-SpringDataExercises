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
@XmlRootElement(name = "supplier")
public class SupplierSimpleExportDto {
  @XmlAttribute
  private Long id;
  @XmlAttribute
  private String name;
  @XmlAttribute(name = "parts-count")
  private Long partsCount;
}
