package com.example.modelmapper.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameDTO {
  private Long id;
  private String title;
  private String trailer;
  private String imageThumbnail;
  private Double size;
  private BigDecimal price;
  private String description;
  private LocalDate releaseDate;
}
