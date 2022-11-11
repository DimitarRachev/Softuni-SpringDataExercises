package com.usersystem.service.impl;

import com.usersystem.repository.AlbumRepository;
import com.usersystem.service.AlbumService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

  private final AlbumRepository albumRepository;

}
