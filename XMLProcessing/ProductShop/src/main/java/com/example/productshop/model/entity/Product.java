package com.example.productshop.model.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
  @Column(nullable = false)
  @Size(min = 3)
  private String name;

  @Column(nullable = false)
  private BigDecimal price;

  @ManyToOne(optional = false, targetEntity = User.class)
  private User seller;

  @ManyToOne(targetEntity = User.class)
  private User buyer;

  @ManyToMany(targetEntity = Category.class)
  private Set<Category> categories;

  public Product() {
    categories = new HashSet<>();
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Product product = (Product) o;
    return name.equals(product.name);
  }

  @Override public int hashCode() {
    return Objects.hash(name);
  }

  public String getSellerFullName() {
    return (seller.getFirstName() == null ? "" : seller.getFirstName()) + " " + seller.getLastName();
  }

  public boolean isSold() {
    return this.buyer != null;
  }
}
