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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class UsersAndProductsWrapperExportDto {
  @XmlAttribute(name = "count")
  private Long usersCount;

  @XmlElement(name = "user")
  private List<UsersWithProductsExportDto> users;

  public UsersAndProductsWrapperExportDto(List<User> users) {
    this.users = new ArrayList<>();
    users.stream().forEach(u -> this.users.add(new UsersWithProductsExportDto(u)));
  }
}
