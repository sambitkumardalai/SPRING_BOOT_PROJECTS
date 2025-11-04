package com.ecom.service;

import java.util.List;

import com.ecom.model.Category;

public interface CategoryService {

	public Category saveCategory(Category category);

	public boolean existCategory(String name);

	public List<Category> getAllCategory();

	public Category getCategoryById(int id);

	public Boolean deleteCategory(int id);

	public List<Category> getAllActiveCategory();
}
