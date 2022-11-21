package com.example.car_dealer.model.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {
  @OneToOne(targetEntity = Car.class)
  private Car car;
  @ManyToOne(targetEntity = Customer.class)
  private Customer customer;

  private Double discount;
}
