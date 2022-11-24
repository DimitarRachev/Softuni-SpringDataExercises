package com.example.cardealer.model.dto.importDto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.cardealer.model.entity.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartImportDto {
  @XmlAttribute(name = "name")
  private String name;
  @XmlAttribute(name = "price")
  private BigDecimal price;
  @XmlAttribute(name = "quantity")
  private Integer quantity;

  private Supplier supplier;
}
