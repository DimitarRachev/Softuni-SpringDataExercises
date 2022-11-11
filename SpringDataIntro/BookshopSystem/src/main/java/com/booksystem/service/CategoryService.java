package com.booksystem.service;

import com.booksystem.model.entity.Category;

import java.util.Set;

public interface CategoryService {
    long count();

    Category save(Category category);

    Set<Category> getRandomCategories();
}
