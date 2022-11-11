package com.usersystem.service.impl;

import com.usersystem.model.entity.Town;
import com.usersystem.repository.TownRepository;
import com.usersystem.service.TownService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class TownServiceImpl implements TownService {

  private final TownRepository townRepository;
private final Random random;

  @Override
  public void save(Town town) {
    townRepository.save(town);
  }

  @Override
  public long count() {
    return townRepository.count();
  }

  @Override
  public Town getRandomTown() {
    long id = random.nextLong(townRepository.count() + 1);
    return townRepository.findByIdIs(id);
  }
}
