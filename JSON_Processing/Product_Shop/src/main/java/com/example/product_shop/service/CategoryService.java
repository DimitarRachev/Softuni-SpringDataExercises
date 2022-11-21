package com.example.product_shop.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.example.product_shop.model.dto.CategoryInfoDto;
import com.example.product_shop.model.entity.Category;

public interface CategoryService {
  void save(Category category);

  Set<Category> getRandomCategories();

  long count();

  List<CategoryInfoDto> findAllByProductCount();
}
