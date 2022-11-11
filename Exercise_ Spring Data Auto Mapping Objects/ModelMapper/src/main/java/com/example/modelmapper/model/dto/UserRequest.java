package com.example.modelmapper.model.dto;

import javax.persistence.Column;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class UserRequest {
  private String email;

  private String password;

  private String fullName;

  public UserRequest(String email, String password, String fullName) {
    this.email = email;
    this.password = password;
    this.fullName = fullName;
  }
}
