package com.example.cardealer.service;

import java.util.List;

import com.example.cardealer.model.dto.exportDto.SupplierSimpleExportDto;
import com.example.cardealer.model.dto.exportDto.SupplierSimpleExportWrapperDto;
import com.example.cardealer.model.entity.Supplier;


public interface SupplierService {
   void save(Supplier supplier);

  long count();
  Supplier getRandomSupplier();

  SupplierSimpleExportWrapperDto getLocalSuppliers();
}
