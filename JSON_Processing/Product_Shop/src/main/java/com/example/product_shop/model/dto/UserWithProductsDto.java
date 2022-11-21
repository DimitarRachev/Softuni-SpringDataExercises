package com.example.product_shop.model.dto;



import com.example.product_shop.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWithProductsDto {
  private String firstName;
  private String lastName;
  private Integer age;
  public AllSoldProductsDto soldProducts;

  public UserWithProductsDto(UserWithProductsDto user) {
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.age = user.getAge();
    this.soldProducts = new AllSoldProductsDto(user.soldProducts.getProducts());
  }
}
