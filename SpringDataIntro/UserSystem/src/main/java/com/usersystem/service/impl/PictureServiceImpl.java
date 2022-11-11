package com.usersystem.service.impl;

import com.usersystem.repository.PictureRepository;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PictureServiceImpl {

  private final PictureRepository pictureRepository;
}
