package com.example.productshop.model.dto.exportDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.productshop.model.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class AllSoldProductExportDto {
  @XmlAttribute(name = "count")
  private Integer count;
  @XmlElement(name = "product")
  private List<ProductSimpleInfoExportDto> products;

  public AllSoldProductExportDto(Set<Product> products) {
    this.count = products.size();
    this.products = new ArrayList<>();
    products
      .stream()
      .forEach(p -> this.products.add(new ProductSimpleInfoExportDto(p)));
  }
}
