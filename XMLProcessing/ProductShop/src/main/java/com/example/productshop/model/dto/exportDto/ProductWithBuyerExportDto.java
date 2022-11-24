package com.example.productshop.model.dto.exportDto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="product")
public class ProductWithBuyerExportDto {
  @XmlElement(name = "name")
  private String name;
  @XmlElement(name = "price")
  private BigDecimal price;
  @XmlElement(name = "buyer-first-name")
  private String buyerFirstName;
  @XmlElement(name = "buyer-last-name")
  private String buyerLastName;
}
