package com.example.modelmapper.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User extends BaseEntity {
  @Column(nullable = false, unique = true)
  private String email;
  @Column(nullable = false)
  private String password;
  @Column
  private String fullName;
  @ManyToMany(targetEntity = Game.class, fetch = FetchType.EAGER)
  private List<Game> games;
  @Column
  @Builder.Default
  private boolean isAdmin = false;
  @OneToMany(mappedBy = "buyer", targetEntity = Order.class)
  private List<Order> orders;

  public User() {
    games = new ArrayList<>();
    orders = new ArrayList<>();
  }

  public void addGame(Game game) {
    games.add(game);
  }
}
