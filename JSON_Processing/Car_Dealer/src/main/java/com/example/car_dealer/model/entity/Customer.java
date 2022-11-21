package com.example.car_dealer.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
@Table
public class Customer extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private LocalDateTime birthDate;

  @Column
  private Boolean isYoungDriver;

  @OneToMany(mappedBy = "customer", targetEntity = Sale.class, fetch = FetchType.EAGER)
  private Set<Sale> sales;


}
