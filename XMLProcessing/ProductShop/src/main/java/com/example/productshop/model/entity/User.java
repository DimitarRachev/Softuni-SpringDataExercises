package com.example.productshop.model.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name", nullable = false)
  @Size(min = 3)
  private String lastName;

  @Column(name = "age")
  private Integer age;

  @OneToMany(mappedBy = "buyer", targetEntity = Product.class)
  private Set<Product> productsBought;

  @OneToMany(mappedBy = "seller", targetEntity = Product.class)
  private Set<Product> productsSold;

  @ManyToMany
  private Set<User> friends;

  public User() {
    productsBought = new HashSet<>();
    productsSold = new HashSet<>();
    friends = new HashSet<>();
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    User user = (User) o;
    return lastName.equals(user.lastName);
  }

  @Override public int hashCode() {
    return Objects.hash(lastName);
  }
}
