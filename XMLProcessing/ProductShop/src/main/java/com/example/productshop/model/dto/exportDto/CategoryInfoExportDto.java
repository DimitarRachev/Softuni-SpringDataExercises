package com.example.productshop.model.dto.exportDto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryInfoExportDto {

  @XmlAttribute(name = "name")
  private String category;

  @XmlElement(name = "products-count")
  private Long categoryCount;

  @XmlElement(name= "average-price")
  private BigDecimal averagePrice;

  @XmlElement(name = "total-revenue")
  private BigDecimal totalRevenue;
}
