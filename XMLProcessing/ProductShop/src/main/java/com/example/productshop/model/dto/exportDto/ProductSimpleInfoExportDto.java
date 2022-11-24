package com.example.productshop.model.dto.exportDto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.productshop.model.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSimpleInfoExportDto {
  @XmlAttribute(name = "name")
  private String name;
  @XmlAttribute(name = "price")
  private BigDecimal price;

  public ProductSimpleInfoExportDto(Product product) {
    this.name = product.getName();
    this.price = product.getPrice();
  }
}
