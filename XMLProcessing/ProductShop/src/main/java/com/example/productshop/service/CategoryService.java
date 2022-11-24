package com.example.productshop.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.example.productshop.model.dto.CategoryInfoDto;
import com.example.productshop.model.dto.exportDto.CategoriesExportWrapperDto;
import com.example.productshop.model.entity.Category;


public interface CategoryService {
  void save(Category category);

  Set<Category> getRandomCategories();

  long count();

  CategoriesExportWrapperDto findAllByProductCount();
}
