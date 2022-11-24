package com.example.productshop.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;


import com.example.productshop.model.dto.CategoryInfoDto;
import com.example.productshop.model.dto.exportDto.CategoriesExportWrapperDto;
import com.example.productshop.model.dto.exportDto.CategoryInfoExportDto;
import com.example.productshop.model.entity.Category;
import com.example.productshop.model.entity.Product;
import com.example.productshop.repository.CategoryRepository;
import com.example.productshop.service.CategoryService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;
  private final ModelMapper mapper;
  private final Random random;

  @Override public void save(Category category) {
    categoryRepository.save(category);
  }

  @Override public Set<Category> getRandomCategories() {
    int numCategories = random.nextInt(3) + 1;
    Set<Category> categories = new HashSet<>();
    long bound = categoryRepository.count();
    for (int i = 0; i < numCategories; i++) {
      Long index = random.nextLong(bound) + 1;
      categories.add(categoryRepository.findById(index).orElseThrow(RuntimeException::new));
    }
    return categories;
  }

  @Override public long count() {
    return categoryRepository.count();
  }

  @Transactional

  @Override public CategoriesExportWrapperDto findAllByProductCount() {
    CategoriesExportWrapperDto toReturn = new CategoriesExportWrapperDto();
    categoryRepository
      .findAll()
      .stream()
      .map(this::makeCategoryInfoDto)
      .sorted(Comparator.comparing(CategoryInfoDto::getCategoryCount).reversed())
      .map(s -> mapper.map(s, CategoryInfoExportDto.class))
      .forEach(s -> toReturn.getCategories().add(s));
    return toReturn;
  }

  private CategoryInfoDto makeCategoryInfoDto(Category category) {

    BigDecimal totalRevenue =
      category.getProducts().stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    return CategoryInfoDto
      .builder()
      .category(category.getName())
      .categoryCount(category.getProducts().size())
      .totalRevenue(totalRevenue)
      .averagePrice(totalRevenue.divide(BigDecimal.valueOf(category.getProducts().size()), 2, RoundingMode.HALF_EVEN))
      .build();
  }
}
