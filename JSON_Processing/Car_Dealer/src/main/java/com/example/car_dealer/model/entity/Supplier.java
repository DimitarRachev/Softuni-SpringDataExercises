package com.example.car_dealer.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Boolean isImporter;

  @OneToMany(targetEntity = Part.class, mappedBy = "supplier")
  private List<Part> parts;

  public Supplier() {
    parts =new ArrayList<>();
  }
}
