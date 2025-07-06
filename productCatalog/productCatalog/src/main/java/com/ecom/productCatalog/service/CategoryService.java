package com.ecom.productCatalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.productCatalog.model.Category;
import com.ecom.productCatalog.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepo = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
