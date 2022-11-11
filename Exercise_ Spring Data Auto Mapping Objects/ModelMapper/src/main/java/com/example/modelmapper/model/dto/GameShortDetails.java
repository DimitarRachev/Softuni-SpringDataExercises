package com.example.modelmapper.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameShortDetails {
  private String title;
  private BigDecimal price;
  private String description;
  private LocalDate releaseDate;

  @Override public String toString() {
    return  new StringBuilder()
      .append("Title: ").append(title).append(System.lineSeparator())
      .append("Price: ").append(price).append(System.lineSeparator())
      .append("Description: ").append(description).append(System.lineSeparator())
      .append("ReleaseDate: ").append(releaseDate).append(System.lineSeparator())
      .toString();
  }
}
