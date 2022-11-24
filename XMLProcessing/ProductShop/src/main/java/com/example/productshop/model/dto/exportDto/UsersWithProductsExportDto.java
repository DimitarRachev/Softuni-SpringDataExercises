package com.example.productshop.model.dto.exportDto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.productshop.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithProductsExportDto {
  @XmlAttribute(name = "first-name")
  private String firstName;
  @XmlAttribute(name = "last-name")
  private String lastName;
  @XmlAttribute(name = "age")
  private Integer age;
  @XmlElement(name = "sold-products")
  private AllSoldProductExportDto soldProducts;

  public UsersWithProductsExportDto(User user) {
    this.firstName = user.getFirstName() == null ? null : user.getFirstName();
    this.lastName = user.getLastName();
    this.soldProducts = new AllSoldProductExportDto(user.getProductsSold());
  }
}
