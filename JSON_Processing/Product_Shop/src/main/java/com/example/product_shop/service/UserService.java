package com.example.product_shop.service;

import java.util.Collection;
import java.util.List;

import com.example.product_shop.model.dto.AllUsersInfoDto;
import com.example.product_shop.model.dto.SellerWithProductsDto;
import com.example.product_shop.model.entity.User;

public interface UserService {
   void save(User user) ;

  User getRandomUser();

  long count();

  List<SellerWithProductsDto> findAllSuccessfullySellers();

  AllUsersInfoDto getUsersAndProducts();
}
