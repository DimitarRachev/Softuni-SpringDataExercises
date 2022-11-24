package com.example.productshop.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;


import com.example.productshop.model.dto.AllSoldProductsDto;
import com.example.productshop.model.dto.AllUsersInfoDto;
import com.example.productshop.model.dto.ProductWithBuyerDto;
import com.example.productshop.model.dto.SellerWithProductsDto;
import com.example.productshop.model.dto.UserWithProductsDto;
import com.example.productshop.model.dto.exportDto.AllSoldProductExportDto;
import com.example.productshop.model.dto.exportDto.SuccessfulSellerWrapperDto;
import com.example.productshop.model.dto.exportDto.UserWithSoldProductsDto;
import com.example.productshop.model.dto.exportDto.UsersAndProductsWrapperExportDto;
import com.example.productshop.model.dto.exportDto.UsersWithProductsExportDto;
import com.example.productshop.model.entity.Product;
import com.example.productshop.model.entity.User;
import com.example.productshop.repository.UserRepository;
import com.example.productshop.service.UserService;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final ModelMapper mapper;
  private final Random random;

  @Override public void save(User user) {
    userRepository.save(user);
  }

  @Override public User getRandomUser() {
    Long index = random.nextLong(userRepository.count()) + 1;
    return userRepository.findById(index).orElseThrow(RuntimeException::new);
  }

  @Override public long count() {
    return userRepository.count();
  }

  @Transactional
  @Override public SuccessfulSellerWrapperDto findAllSuccessfullySellers() {
    SuccessfulSellerWrapperDto toReturn = new SuccessfulSellerWrapperDto();
     userRepository.findAllByProductsSoldBuyerIsNotNullOrderByLastNameAscFirstNameAsc()
       .get()
      .stream()
      .map(UserServiceImpl::makeSellerWithProductDto)
       .map(s -> mapper.map(s, UserWithSoldProductsDto.class))
      .forEach(s -> toReturn.getUsers().add(s));
    return toReturn;
  }

  @Transactional
  @Override public UsersAndProductsWrapperExportDto getUsersAndProducts() {
    return new UsersAndProductsWrapperExportDto(userRepository.findAllByProductsSoldBuyerIsNotNullOrderByProductsSoldDescLastName());
  }

  private static SellerWithProductsDto makeSellerWithProductDto(User u) {
    return SellerWithProductsDto
      .builder()
      .firstName(u.getFirstName() == null ? null : u.getFirstName())
      .lastName(u.getLastName())
      .products(u.getProductsSold()
        .stream()
        .map(UserServiceImpl::makeProductWithBuyerDto)
        .toList())
      .build();
  }

  private static ProductWithBuyerDto makeProductWithBuyerDto(Product p) {
    return ProductWithBuyerDto
      .builder()
      .name(p.getName())
      .price(p.getPrice())
      .buyerFirstName(p.getBuyer() == null || p.getBuyer().getFirstName() == null ? null : p.getBuyer().getFirstName())
      .buyerLastName(p.getBuyer() == null ? null : p.getBuyer().getLastName())
      .build();
  }
}
