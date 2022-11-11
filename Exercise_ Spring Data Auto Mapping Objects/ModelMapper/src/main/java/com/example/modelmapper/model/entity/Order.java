package com.example.modelmapper.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")

@AllArgsConstructor
@Builder
public class Order extends BaseEntity {
  @ManyToOne(targetEntity = User.class)
  private User buyer;

  @ManyToMany(targetEntity = Game.class)
  private List<Game> products;

  @Transient
  private BigDecimal totalPrice;

  public Order() {
    products = new ArrayList<>();
  }

  public BigDecimal getTotalPrice() {
    return products.isEmpty()
      ? BigDecimal.ZERO
      : products
         .stream()
         .map(Game::getPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
