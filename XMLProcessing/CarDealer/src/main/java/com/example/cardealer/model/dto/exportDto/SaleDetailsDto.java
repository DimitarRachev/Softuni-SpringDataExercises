package com.example.cardealer.model.dto.exportDto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleDetailsDto {
  @XmlElement
  private CarSimpleExportDto car;
  @XmlElement(name = "customer-name")
  private String customerName;
  @XmlElement(name = "discount")
  private Double discount;
  @XmlElement(name = "price")
  private BigDecimal price;
  @XmlElement(name = "price-with-discount")
  private BigDecimal priceWithDiscount;
}
