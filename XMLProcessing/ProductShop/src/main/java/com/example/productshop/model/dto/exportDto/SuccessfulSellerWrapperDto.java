package com.example.productshop.model.dto.exportDto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="users")
public class SuccessfulSellerWrapperDto {
  @XmlElement(name="user")
  private List<UserWithSoldProductsDto> users;


  public SuccessfulSellerWrapperDto() {
    users = new ArrayList<>();
  }
}
