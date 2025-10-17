package com.ecom.service;

import java.util.List;

import com.ecom.model.Category;

public interface CategoryService {

	public Category saveCategory(Category category);

	public boolean existCategory(String name);

	public List<Category> getAllCategory();
}
