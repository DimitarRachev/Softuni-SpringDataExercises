package com.example.modelmapper.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "games")
public class Game extends BaseEntity{
  @Column(nullable = false, unique = true)
  private String title;
  @Column
  private String trailer;
  @Column
  private String imageThumbnail;
  @Column
  private Double size;
  @Column(nullable = false)
  private BigDecimal price;
  @Column
  private String description;
  @Column
  private LocalDate releaseDate;

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Game game = (Game) o;
    return title.equals(game.title);
  }

  @Override public int hashCode() {
    return Objects.hash(title);
  }
}
