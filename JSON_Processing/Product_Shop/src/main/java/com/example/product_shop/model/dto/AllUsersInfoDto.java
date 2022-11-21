package com.example.product_shop.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllUsersInfoDto {
  private int usersCount;
  List<UserWithProductsDto> users;

  public AllUsersInfoDto(List<UserWithProductsDto> users) {
    this.users = users;
    usersCount = users.size();
  }
}
