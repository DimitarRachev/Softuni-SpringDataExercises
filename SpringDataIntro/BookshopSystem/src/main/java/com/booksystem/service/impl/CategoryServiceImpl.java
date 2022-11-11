package com.booksystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.booksystem.model.entity.Category;
import com.booksystem.repository.CategoryRepository;
import com.booksystem.service.CategoryService;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final Random random;

    @Override
    public long count() {
        return categoryRepository.count();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Set<Category> getRandomCategories() {
        int size = (int)categoryRepository.count();
        int categoriesCount = random.nextInt(size) + 1;
        Set<Category> categories = new HashSet<>();
        for(int i = 0; i < categoriesCount; i++){
            long nextId = random.nextLong(size) + 1;
            categories.add(categoryRepository.findById(nextId).orElseThrow(RuntimeException::new));
        }
        return categories;
    }
}
