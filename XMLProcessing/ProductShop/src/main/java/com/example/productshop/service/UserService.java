package com.example.productshop.service;

import java.util.Collection;
import java.util.List;

import com.example.productshop.model.dto.AllUsersInfoDto;
import com.example.productshop.model.dto.SellerWithProductsDto;
import com.example.productshop.model.dto.exportDto.SuccessfulSellerWrapperDto;
import com.example.productshop.model.dto.exportDto.UsersAndProductsWrapperExportDto;
import com.example.productshop.model.entity.User;


public interface UserService {
   void save(User user) ;

  User getRandomUser();

  long count();

  SuccessfulSellerWrapperDto findAllSuccessfullySellers();

  UsersAndProductsWrapperExportDto getUsersAndProducts();
}
