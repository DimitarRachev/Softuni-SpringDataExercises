package com.example.cardealer.service.impl;

import java.util.List;
import java.util.Random;


import com.example.cardealer.model.dto.exportDto.SupplierSimpleExportDto;
import com.example.cardealer.model.dto.exportDto.SupplierSimpleExportWrapperDto;
import com.example.cardealer.model.entity.Supplier;
import com.example.cardealer.repository.SupplierRepository;
import com.example.cardealer.service.SupplierService;

import org.modelmapper.ModelMapper;
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

  @Override public SupplierSimpleExportWrapperDto getLocalSuppliers() {
    SupplierSimpleExportWrapperDto toReturn = new SupplierSimpleExportWrapperDto();
    repository.findAllByIsImporterFalse().forEach(s -> toReturn.getSuppliers().add(s));
    return toReturn;
  }
}
