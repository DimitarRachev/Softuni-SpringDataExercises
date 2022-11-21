package com.example.car_dealer.service.impl;

import java.util.List;
import java.util.Random;

import com.example.car_dealer.model.dto.SupplierSimpleExportDto;
import com.example.car_dealer.model.entity.Supplier;
import com.example.car_dealer.repository.SupplierRepository;
import com.example.car_dealer.service.SupplierService;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
  private final SupplierRepository repository;
  private final Random random;
  private final ModelMapper mapper;

  @Override public void save(Supplier supplier) {
    repository.save(supplier);
  }

  @Override public long count() {
    return repository.count();
  }

  @Override public Supplier getRandomSupplier() {
    long id = random.nextLong(repository.count()) + 1L;
    return repository.findById(id).orElseThrow(RuntimeException::new);
  }

  @Override public List<SupplierSimpleExportDto> getLocalSuppliers() {

    return repository.findAllByIsImporterFalse();
  }
}
