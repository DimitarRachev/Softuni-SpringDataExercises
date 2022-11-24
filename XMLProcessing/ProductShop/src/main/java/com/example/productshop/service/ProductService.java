package com.example.productshop.service;


import com.example.productshop.model.dto.exportDto.ProductExportWrapperDto;
import com.example.productshop.model.entity.Product;


public interface ProductService {
  void save(Product product);

  long count();

  ProductExportWrapperDto findAllWithPriceBetweenWithNoSeller(double lowerBound, double upperBound);
}
