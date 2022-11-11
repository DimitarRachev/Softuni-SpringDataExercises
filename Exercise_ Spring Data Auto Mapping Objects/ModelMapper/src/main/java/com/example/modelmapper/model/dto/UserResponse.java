package com.example.modelmapper.model.dto;

import java.util.List;


import com.example.modelmapper.model.entity.Game;
import com.example.modelmapper.model.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserResponse {
  private String email;
  private String fullName;
  private List<Game> games;
  private boolean isAdmin = false;
  private List<Order> orders;
}
