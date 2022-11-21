package com.example.car_dealer.repository;

import java.util.List;

import com.example.car_dealer.model.dto.SupplierSimpleExportDto;
import com.example.car_dealer.model.entity.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
  @Query("select new com.example.car_dealer.model.dto.SupplierSimpleExportDto(" +
    "s.id, s.name, count(p.id)) " +
    "from Supplier s " +
    "join Part p " +
    "on p.supplier.id = s.id " +
    "where s.isImporter = false " +
    "group by s.id " +
    "order by count(p.id) desc ")
  List<SupplierSimpleExportDto> findAllByIsImporterFalse();
}
