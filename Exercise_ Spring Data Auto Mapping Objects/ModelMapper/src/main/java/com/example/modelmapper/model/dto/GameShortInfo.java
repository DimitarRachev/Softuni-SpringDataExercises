package com.example.modelmapper.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameShortInfo {
  private String title;
  private BigDecimal price;

  @Override public String toString() {
    return title + " " + price;
  }
}
