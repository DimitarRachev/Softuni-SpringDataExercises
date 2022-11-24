package com.example.cardealer.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
  @Column(nullable = false)
  private String make;
  @Column(nullable = false)
  private String model;
  @Column(nullable = false)
  private Long travelledDistance;
  @ManyToMany(targetEntity = Part.class)
  private List<Part> parts;

}
