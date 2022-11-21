package com.example.car_dealer.service;

import java.util.Collection;
import java.util.List;

import com.example.car_dealer.model.dto.SupplierSimpleExportDto;
import com.example.car_dealer.model.entity.Supplier;

public interface SupplierService {
   void save(Supplier supplier);

  long count();
  Supplier getRandomSupplier();

  List<SupplierSimpleExportDto> getLocalSuppliers();
}
