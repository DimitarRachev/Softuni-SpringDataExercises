package com.example.product_shop.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.example.product_shop.model.dto.AllUsersInfoDto;
import com.example.product_shop.model.dto.ProductWithBuyerDto;
import com.example.product_shop.model.dto.SellerWithProductsDto;
import com.example.product_shop.model.dto.UserWithProductsDto;
import com.example.product_shop.model.entity.Product;
import com.example.product_shop.model.entity.User;
import com.example.product_shop.repository.UserRepository;
import com.example.product_shop.service.UserService;

import org.modelmapper.ModelMapper;
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
  @Override public List<SellerWithProductsDto> findAllSuccessfullySellers() {
    return userRepository.findAllByProductsSoldBuyerIsNotNull().get()
      .stream()
      .map(UserServiceImpl::makeSellerWithProductDto)
      .sorted(Comparator.comparing(SellerWithProductsDto::getLastName))
      .toList();
  }

  @Transactional
  @Override public AllUsersInfoDto getUsersAndProducts() {

    List<UserWithProductsDto> userWithProductsDtos =
      userRepository.findAllByProductsSoldBuyerIsNotNullOrderByProductsSoldDescLastName()
        .stream()
        .map(u -> mapper.map(u, UserWithProductsDto.class))
        .collect(Collectors.toList());
    return new AllUsersInfoDto(userWithProductsDtos);
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
