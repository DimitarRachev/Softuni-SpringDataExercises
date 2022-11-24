package com.example.cardealer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import com.example.cardealer.model.entity.Part;
import com.example.cardealer.repository.PartRepository;
import com.example.cardealer.service.PartService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {
  private final PartRepository partRepository;
  private final Random random;

  @Override public void save(Part part) {
    partRepository.save(part);
  }

  @Override public long count() {
    return partRepository.count();
  }

  @Override public List<Part> getRandomParts() {
    int size = random.nextInt(3) + 3;
    List<Part> parts = new ArrayList<>();
    long count = partRepository.count();
    for (int i = 0; i < size; i++) {
      parts.add(partRepository.findById(random.nextLong(count) + 1).orElseThrow(RuntimeException::new));
    }
    return parts;
  }
}
