package com.example.productshop.model.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

  @Column(nullable = false)
  @Size(min = 3, max = 15)
  private String name;

  @ManyToMany(mappedBy = "categories", targetEntity = Product.class)
  private Set<Product> products;

  public Category() { products = new HashSet<Product>(); }



  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Category category = (Category) o;
    return name.equals(category.name);
  }

  @Override public int hashCode() {
    return Objects.hash(name);
  }
}
