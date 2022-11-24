package com.example.productshop.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


import com.example.productshop.model.dto.ProductExportDto;
import com.example.productshop.model.dto.exportDto.ProductExportWrapperDto;
import com.example.productshop.model.dto.exportDto.ProductSimpleExportDto;
import com.example.productshop.model.entity.Product;
import com.example.productshop.repository.ProductRepository;
import com.example.productshop.service.ProductService;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;
  private final ModelMapper modelMapper;

  @Override public void save(Product product) {
    productRepository.save(product);
  }

  @Override public long count() {
    return productRepository.count();
  }

  @Override public ProductExportWrapperDto findAllWithPriceBetweenWithNoSeller(double lowerBound, double upperBound) {
    ProductExportWrapperDto toReturn = new ProductExportWrapperDto();
    TypeMap<Product, ProductSimpleExportDto> propertymapper =
      modelMapper.createTypeMap(Product.class, ProductSimpleExportDto.class);
    propertymapper.addMappings(mapper -> mapper.map(Product::getSellerFullName, ProductSimpleExportDto::setSeller));
    productRepository
      .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal.valueOf(lowerBound), BigDecimal.valueOf(upperBound))
      .stream()
      .map(p -> modelMapper.map(p, ProductSimpleExportDto.class))
      .forEach(p -> toReturn.getProducts().add(p));
    return toReturn;
  }
}
