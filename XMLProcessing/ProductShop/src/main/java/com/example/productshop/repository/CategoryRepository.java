package com.example.productshop.repository;

import java.util.List;


import com.example.productshop.model.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  List<Category> findAllByIdGreaterThanOrderByProducts(Long id);
}
